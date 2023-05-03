package com.proyecto.integrador.utils;

public class Validaciones {
	
			//El texto solo permite caracteres 
			public static final String TEXT = "^[A-Za-z\\s]+$"; 
			
			//El telefono debe contener 9 digitos iniciar con 9 y cualquier digitos
			public static final String PHONE = "^9[\\d]{8}$"; 
			
			//Valida el email
			public static final String EMAIL = "^[\\w-]+(\\.[\\w-]+)*@[A-Za-z0-9]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
			
			//El numero de ruc debe contener 11 digitos y debe iniciar con 10 o 20
			public static final String RUC = "^(10|20)[\\d]{9}$";
			
			//El numero de dni debe contener 8 digitos
			public static final String DNI = "^[\\d]{8}$";
			
			//La contraseña debe ser minimo de 8 caracteres, contar con al menos 1 caracter especial y al menos un numero
			public static final String PASSWORD = "^(?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[A-Za-z]).{8,}$";
}
