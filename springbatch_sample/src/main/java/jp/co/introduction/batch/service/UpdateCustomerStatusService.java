package jp.co.introduction.batch.service;

import javax.sql.DataSource;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecutionListener;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.database.BeanPropertyItemSqlParameterSourceProvider;
import org.springframework.batch.item.database.JdbcBatchItemWriter;
import org.springframework.batch.item.database.builder.JdbcCursorItemReaderBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;

import jp.co.introduction.batch.entity.CustomerEntity;
import jp.co.introduction.batch.entity.CustomerRowMapper;
import jp.co.introduction.batch.listener.JobStartEndListener;
import jp.co.introduction.batch.model.CustomerUpdateModel;
import jp.co.introduction.batch.processor.UpdateCustomerStatusServiceProcessor;

/**
 * 会員テーブル(CUSTOMER)の会員ステータス(CUSTOMER_STATUS)を退会済みに更新する
 */
@Configuration
@EnableBatchProcessing
public class UpdateCustomerStatusService {

	@Autowired
	public JobBuilderFactory jobBuilderFactory;
	@Autowired
	public StepBuilderFactory stepBuilderFactory;
	@Autowired
	public DataSource dataSource;

	/**
	 * Job<br>
	 * ここから後続の処理が呼び出される
	 */
	@Bean
	public Job updateJob() {
		return jobBuilderFactory.get("updateJob").incrementer(new RunIdIncrementer())
				// listener呼び出し
				.listener(this.updateServiceListener())
				// step(メイン処理)呼び出し
				.flow(this.updateServiceStep())
				// 終了
				.end().build();
	}

	/**
	 * Listener<br>
	 * Jobの開始と終了を管理する
	 */
	@Bean
	public JobExecutionListener updateServiceListener() {
		return new JobStartEndListener(new JdbcTemplate(dataSource));
	}

	/**
	 * Step<br>
	 * メイン処理<br>
	 * Reader-Processor-Writerを順番に呼び出す
	 */
	@Bean
	public Step updateServiceStep() {
		return stepBuilderFactory.get("step")
				// 処理するデータ型の指定(Customer)と処理単位の指定(10件ずつ)を行う
				.<CustomerEntity, CustomerUpdateModel>chunk(10)
				// 処理対象データの読み込み
				.reader(this.updateServiceReader())
				// データ加工処理
				.processor(this.updateServiceProcessor())
				// データ書き込み処理
				.writer(this.updateServiceWriter()).build();
	}

	/**
	 * Reader<br>
	 * DBのデータを読み込み、stepへ返却する。
	 */
	@Bean
	public ItemReader<CustomerEntity> updateServiceReader() {
		StringBuilder sb = new StringBuilder();
		// 会員テーブルのデータを取得
		sb.append("SELECT ");
		sb.append("  CUSTOMER_CODE ");
		sb.append("  , FIRST_NAME ");
		sb.append("FROM ");
		sb.append("  CUSTOMER ");

		return new JdbcCursorItemReaderBuilder<CustomerEntity>().dataSource(dataSource).name("jdbc-reader")
				.sql(sb.toString()).rowMapper(new CustomerRowMapper()).build();
	}

	/**
	 * Processor<br>
	 * Readerで読み込んだデータの加工を行う
	 */
	@Bean
	public ItemProcessor<CustomerEntity, CustomerUpdateModel> updateServiceProcessor() {
		// ProcessedServiceProcessorでデータの加工処理
		return new UpdateCustomerStatusServiceProcessor();
	}

	/**
	 * Writer<br>
	 * Processorで加工したデータを読み込みデータベースへ更新を行う
	 */
	@Bean
	public JdbcBatchItemWriter<CustomerUpdateModel> updateServiceWriter() {
		// 書き込み情報の共通設定
		JdbcBatchItemWriter<CustomerUpdateModel> writer = new JdbcBatchItemWriter<CustomerUpdateModel>();
		writer.setItemSqlParameterSourceProvider(new BeanPropertyItemSqlParameterSourceProvider<CustomerUpdateModel>());
		writer.setDataSource(dataSource);

		// CustomerUpdateModelに定義したフィールド名でplaceHolder(:customerCode)との紐付けを行い、データをバインドする
		StringBuilder sb = new StringBuilder();
		sb.append("UPDATE ");
		sb.append("  CUSTOMER ");
		sb.append("SET ");
		sb.append("  CUSTOMER_STATUS = :customerStatus ");
		sb.append("WHERE ");
		sb.append("  CUSTOMER_CODE = :customerCode ");
		writer.setSql(sb.toString());
		return writer;
	}
}
