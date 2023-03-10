import java.util.Scanner;
import java.util.regex.Pattern;

public class tarjeta_Credito {

	static Scanner Leer = new Scanner(System.in);
	
	static int SIZE = 15+1;
	static int[] array = new int[SIZE];
	static String tarjeta_string;

	static int pedir_num(){//Funci�n para pedir cada n�mero
		int num;
		System.out.println("Ingrese digito (0-9): ");
		num = Leer.nextInt(); 
		return num;
	}

	static int valida(int num){//Funci�n para validar que siempre se ingrese un n�mero de un d�gito
		if(num<0){
			System.out.println("digito invalido, ingrese digito nuevamente: ");
			num = pedir_num();
		}
		if(num>9){
			System.out.println("digito invalido, ingrese digito nuevamente: ");
			num = pedir_num();
		}
		return num;
	}

	static void pedir_tarjeta(){//Funci�n que llama a las funciones ya creadas para llenar un arreglo con los n�meros de la tarjeta
		
		for(int i=0;i<SIZE;i++){
			System.out.println("Digito "+(i+1));
			int num = pedir_num();
			num = valida(num);
			array[i]=num;
			if(i==0) {//Se convierte en string para poder validarla que tipo es según el formato
				tarjeta_string = Integer.toString(array[i]);
			}else{
				tarjeta_string += Integer.toString(array[i]);
			}
		}

		System.out.println();
		System.out.println("Tarjeta numero: "+tarjeta_string);
		System.out.println();
	}

	static void check(int[] array){//Funci�n para hacer la validaci�n solicitada

		int[] array_2= new int[SIZE];
		
		for(int i = 0; i < SIZE; i++){
			array_2[i]=array[i];
		}
		
		int cont = 0;
		System.out.println("Proceso de validacion:\n\n");
		System.out.println("Multiplicar posiciones pares por 2");
		for(int i = 0; i < SIZE; i++){
			if(i%2==0){
				array_2[i]=array_2[i]*2;
			}
			System.out.print(array_2[i]+" ");
		}
		System.out.println();
		System.out.println("Restar 9 a los numeros de dos digitos");
		for(int i = 0; i < SIZE; i++){
			if(array_2[i]>9){
				array_2[i]=array_2[i]-9;
			}
			System.out.print(array_2[i]+" ");
			cont = cont + array_2[i];	
		}
		System.out.println();
		System.out.println("Se suman todos los digitos = "+cont+", Se saca el modulo en 10");
		
		if (cont%SIZE==0){
			System.out.println("---La tarjeta SI es valida---");
		}else{
			System.out.println("---la tarjeta NO es valida---");
		}
	}

public enum Tipo_Tarjeta {
	//Se definen los formatos en rejex para formar los tipos de tarjeta que se van a validar
    DESCONOCIDO,
    VISA("^4[0-9]{12}(?:[0-9]{3}){0,2}$"),
    MASTERCARD("^(?:5[1-5]|2(?!2([01]|20)|7(2[1-9]|3))[2-7])\\d{14}$"),
    AMERICAN_EXPRESS("^3[47][0-9]{13}$"),
    DINERS_CLUB("^3(?:0[0-5]\\d|095|6\\d{0,2}|[89]\\d{2})\\d{12,15}$"),
    DISCOVER("^6(?:011|[45][0-9]{2})[0-9]{12}$"),
    JCB("^(?:2131|1800|35\\d{3})\\d{11}$"),
    CHINA_UNION_PAY("^62[0-9]{14,17}$");

    private Pattern pattern;
    
    //Se crea un método constructor para cuando es desconocido
    Tipo_Tarjeta() {
        this.pattern = null;
    }
    // Se hace una sobre carga para poder pasar el formato que creamos
    Tipo_Tarjeta(String pattern) {
        this.pattern = Pattern.compile(pattern);
    }

    public static Tipo_Tarjeta detect(String cardNumber) {//Se crea una función para validar el formato

        for (Tipo_Tarjeta tipo_Tarjeta : Tipo_Tarjeta.values()) {//Hacemos un for que recorre todos los formatos
            if (null == tipo_Tarjeta.pattern) continue; //sio el formato es vacio entonces pasara afuera y devolvera deconocido
            if (tipo_Tarjeta.pattern.matcher(cardNumber).matches()) return tipo_Tarjeta; // Si hace match con algún formato, nos lo devuelve
        }

        return DESCONOCIDO;
    }

}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//4 1 6 8 8 1 8 8 4 4 4 4 7 1 1 5
		int op;
		do{
			pedir_tarjeta();
			check(array);
			System.out.println("------------TARJETA TIPO "+Tipo_Tarjeta.detect(tarjeta_string)+"--------------------");
			System.out.println("Pulse 1 para volver a validar otra tarjeta:  ");
			op = Leer.nextInt();
		}while(op==1);
		System.out.println("Finalizo el programa :D");
	}

}
