CREATE TABLE OTP (
	id SERIAL PRIMARY KEY,
	email VARCHAR(256),
	otp INT,
	expiry_date DATE
);