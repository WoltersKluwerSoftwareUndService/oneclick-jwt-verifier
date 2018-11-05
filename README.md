# Oneclick JWT verifier

## Description
This tool makes a simple direct login with the provided data. 
After the login was successful, the corresponding member is determined. 
With the given information (member, secret) finally the JWT is verified. 
The verification takes place on the basis of some exemplary claims, the Issuer and the Secret.

## Usage

Build the artifacts by calling `mvn package`

To execute the provided tool pass the following parameters and environment variables which allows to verify a oneclick jwt

Parameter
- OneClickUrl = Url of used system
- CustomerUrl = Known url of tax advisor (customer name)
- ClientNumber = Unique client number (for tax advisor logins not needed)
- User = Username to login
- Password = Password of user login

System-Variables

- oneclick_client_secret = Secret to verify the client
- oneclick_jwt_secret = Secret to verify the retrieved access token (JWT)

```
export oneclick_client_secret=<JWT-Secret>
export oneclick_jwt_secret=<Client-Secret>
java -jar de.wk.jwt-verifier-1.0-SNAPSHOT-jar-with-dependencies.jar <OneClickUrl> <CustomerUrl> <ClientNumber> <User> <Password>

export oneclick_client_secret=abc123
export oneclick_jwt_secret=xyz789
java -jar de.wk.jwt-verifier-1.0-SNAPSHOT-jar-with-dependencies.jar portal-bereich.de teststb 123 testuser password123
```

