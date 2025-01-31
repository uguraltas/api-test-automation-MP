# API Test Case Study



**Installations**

*   _Java 17_ [https://adoptium.net/temurin/releases/?package=jdk&version=17](https://adoptium.net/temurin/releases/?package=jdk&version=17)
*   _Maven_ [https://maven.apache.org/download.cgi](https://maven.apache.org/download.cgi)
*   _npm_ [https://docs.npmjs.com/downloading-and-installing-node-js-and-npm](https://docs.npmjs.com/downloading-and-installing-node-js-and-npm)

1. Run Cucumber API Tests:

   ```bash
   mvn clean test verify -Dsurefire.suiteXmlFiles=src/test/resources/testng.xml
   
2. Install npm:
	
   ```bash
   npm install

3. Run postman collection tests with newman:

   ```bash
   npm run test_postman_collection

4. Install webdriver-manager:

    ```bash
    pip3 install webdriver-manager

**Reports**

To view the test execution reports, please follow:
    
  [extent-html-report.html](target/extent-reports/extent-html-report.html)

    
