# PixeonTech Challenge Solution

## How to run

To run the application, use the IDE of your preference and import the project as a **gradle project** and run.

## API 

### Create Health Care Institution

> URL: ``/hc/create`` 
> Type: ``POST``
> Body: ``JSON``
> | Field | Type | Description | 
> | ----- | ----- | ----- |
> | name | String | Health Care Institution Name |  
> | cnpj | String | Health Care Institution CNPJ | 
>
> E.g.:
> ```
>{
>    "name" : "Baia Sul",
>    "cnpj": "000000.000000.00000/0000-1" 
>}


### Create Exam

> URL: ``/exam/create`` 
> Type: ``POST``
> Body: ``JSON``
> | Field | Type |  Description |
> | ----- | ----- | ----- |
> | institutionCNPJ | String | Exam's Institution CNPJ | 
> | procedureName | String | Exam's procedures name | 
> | physicianCRM | String | Doctor's credential key |
> | physicianName |String | Doctor's name |
> |patientGender| char| Patient's gender|
> |patientAge|String |Patient's age| 
> |patientName| String|Patient's name|
> 
>E.g.:
> ```
> {
> "institutionCNPJ": "000000.000000.00000/0000-1",
> "procedureName" : "X-Ray",
> "physicianCRM" : "CR-23596",
> "physicianName" : "Dr House",
> "patientGender" : "m",
> "patientAge" : "36",
> "patientName" : "Bruce Wayne"
> }
> ```

### Find Exam
> URL: ``/exam/find`` 
> Type: ``GET``
> Parameters: ``QueryParam``
> | Field | Type | Description |
> | ----- | ----- | ----- |
> | identifier | int | Unique ID for the Exam | 
> | institutionCnpj | String | ID for the Health Care Institution where the exam occurred | 
> 
>E.g. If the server is running on localhost:8080:
> ```
> http://localhost:8080/exam/find?identifier=1&institutionCnpj=000000.000000.00000/0000-1
> ```

### Update Exam
> URL: ``/exam/update`` 
> Type: ``PUT``
> Body: ``JSON``
> | Field | Type |  Description |
> | ----- | ----- | ----- |
> | identifier | int | Exam's ID |
> | institutionCNPJ | String | Exam's Institution CNPJ | 
> | procedureName | String | Exam's procedures name | 
> | physicianCRM | String | Doctor's credential key |
> | physicianName |String | Doctor's name |
> |patientGender| char| Patient's gender|
> |patientAge|String |Patient's age| 
> |patientName| String|Patient's name|
> 
>E.g.:
> ```
> {
> "identifier": 312,
> "institutionCNPJ": "000000.000000.00000/0000-1",
> "procedureName" : "T-virus extraction",
> "physicianCRM" : "MD-23596",
> "physicianName" : "Dr Willian Birkin",
> "patientGender" : "m",
> "patientAge" : "21",
> "patientName" : "Leon S. Kennedy"
> }
> ```



### Delete Exam
> URL: ``/exam/update`` 
> Type: ``DELETE``
> Parameters: ``QueryParam``
> | Field | Type | Description |
> | ----- | ----- | ----- |
> | identifier | int | Unique ID for the Exam | 
> | institutionCnpj | String | ID for the Health Care Institution where the exam occurred | 
> 
>E.g. If the server is running on localhost:8080:
> ```
> http://localhost:8080/exam/find?identifier=1&institutionCnpj=000000.000000.00000/0000-1
> ```




## To Do (didn't have enough time):

- Add more valuable validation to exam creation;
- Add more tests to make sure that Validators and the Repositories are working as expected;
- Update the return method when saved an Exam, so the user can check the exam saved and it's ID;
- Add tests to, mainly to the garantee the logic behind the ExamRepository and ExamValidator;
- Worked better with the responses from the API;
