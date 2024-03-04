1. Create Employee: Users can create a new employee by sending a POST request to the /api/v1/employee/CreateEmployee/ endpoint with the required request body parameters: employee_name, employeeCode, employee_dept, and role.

2. Register Password: After creating an employee, users must register a password by sending get request to the /api/v1/employee/registerEmployee/{employeeId} endpoint, passing the employeeId as a URL parameter.

3. Login: Users can log in by sending a POST request to the /api/v1/employee/login endpoint with the employeeCode and password in the request body. Upon successful login, a JWT token is returned.

4. Test API: The /api/v1/employee/test/ endpoint is provided for testing purposes. Users must include the JWT token in the Authorization header to access this endpoint.
