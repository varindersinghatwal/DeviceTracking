# Getting Started

### Reference Documentation
Demo spring boot project to persist the device information in no-sql store system like DynamoDB.

![devicetracking drawio](https://github.com/varindersinghatwal/DeviceTracking/assets/18482454/c4fb103e-506c-4c76-9a89-97058033e03f)

### How to start the service
1. Clone this project:
    ```
   git clone https://github.com/varindersinghatwal/DeviceTracking.git .
   ```
2. Go to the main directory of the project
    ```
   cd DeviceTracking/
   ```
3. Start the spring boot service
    ```
   mvn clean spring-boot:run
   ```
   
### How to test the service
Run the main method of ```TestApiUsingSDK``` class. <b>Make sure to start the service using above steps.</b> It has the simple test case which call following APIs of the service:
1. ```AddDeviceInfo``` api - It persist the device information in the memory and data accessor class mimic the no-sql functionality.
2. ```GetDeviceInfo``` api - This API returns the latest device information available in the storage.
3. ```GetDeviceInfoHistory``` api - This API returns all the historical device information.

### Data persistence
Data can be stored in no-sql database because is semi-structure and there is no need of relational data management.
1. Hash key should be - Device id + timestamp (time at which device information was captured).
2. Sort key should be - timestamp.

If we don't want the history of device information udates then we can simplify the schema and make the device id as hash key.
