# springboot_springthymeleaf_sample

## application-package
- sample-api  
- sample-web  

## dependency-package
- sample-base  
- sample-common  
- sample-biz  

## details
### 1.sample-api
  Spring Bootsample code.  
  Call the released product search API  
  ex/ http://localhost:1192/sample-api/v1/items?keyword=sony

### 2.sample-web
  SpringThymeleaf sample code.  
  In product search, call "sample-api".  
  ex/ http://localhost:9000/app/search
  
### 3.sample-base
  Handle "base classes" (filters, handlers, response models).

### 4.sample-common
  Handle requests, responses, and models.

### 5.sample-biz
  Handle common processing.
