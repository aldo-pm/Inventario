
package proyecto.inventario;


import java.io.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.chrono.ChronoLocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;
import java.util.Iterator;
import java.util.HashMap;

public class Inventario {


    public static Scanner leer = new Scanner(System.in);
    public static String outputAdministradores = "C:\\Users\\aldop\\Desktop\\8 sem\\Taller de productividad basada en herramientas tecnológicas\\Inventario\\com.mycompany_Inventario\\src\\main\\java\\proyecto\\inventario\\Administradores.csv";

    
    public static void main(String[] args) throws IOException, ParseException{
        



            BufferedWriter bwAdmin = new BufferedWriter(new FileWriter(outputAdministradores, true));

            String line;

            leer.useDelimiter("\n");
            int opc, ban = 0, ban1 = 0;
            boolean acceso = false;
            
            HashMap<String, String> mapa = new HashMap<String, String>();  
            
            System.out.println(" Inventario \n");
            
            do{     
                ban1 = archivV("C:\\Users\\aldop\\Desktop\\8 sem\\Taller de productividad basada en herramientas tecnológicas\\Inventario\\com.mycompany_Inventario\\src\\main\\java\\proyecto\\inventario\\Administradores.csv"); //llamado a metodo que rectifica que el archivo no esté vacio
                if(ban1 == 1)
                {
                    System.out.println("Ingreso solo para administradores\n");
                    System.out.println("Ingresar ID: ");
                    String id = leer.next();
                    System.out.println("Ingresar contraseña: ");
                    String pass = leer.next();
                    
                    load(mapa);  

                    acceso = contrasena(mapa,id,pass);  

                }
                else           
                {    
                    System.out.println("No existen Administradores");
                    System.out.println(" Dar de alta Administrador \n");
                    System.out.println("Ingresar ID: ");
                    String id = leer.next();
                    System.out.println("Ingresar contraseña: ");
                    String pass = leer.next();                    

                    creaAdministrador(mapa, id, pass, bwAdmin); 

                }
                
            }while ( acceso == false ); 
            
                do         
                {
                    try{                                                                    

                    System.out.println("\nSelecciona la opción que se desea:\n");                
                        System.out.println("Dar de alta Administrador  =  1");
                                
                        System.out.println("Salir  =  0");

                        opc = leer.nextInt();

                        
                        switch(opc)
                        {
                            case 1:

                                System.out.println(" Dar de alta Administrador \n");
                                System.out.println("Ingresae  ID: ");
                                String id = leer.next();
                                System.out.println("Ingresar contraseña: ");
                                String pass = leer.next();                    

                                creaAdministrador(mapa, id, pass, bwAdmin);       

                                break;

                         
                            case 0:

                                System.out.println("Se ha salido del programa");     
                                ban = 1;                            
                                break;
                            default:
                                System.out.println("Opción no correcta\n");  
                                break;


                        }
                    }
                    catch (Exception e)             
                    {
                        System.out.println("Error \n");
                        break;

                    }
                }while(ban == 0);
        
    }
    
    
    public static int archivV(String archivo)
    {
        int i = 0;
        
        try
        {
            BufferedReader br = new BufferedReader(new FileReader(archivo));     
            if (br.readLine() == null) 
            {
                System.out.println("archivo vacio");
            }
            
            else i = 1;
        }
        catch(IOException e) {
            System.out.println("IOException catched while writing: " + e.getMessage());
        }
        return i;
    }
    
    
    
    public static void creaAdministrador(HashMap <String,String> mapa, String id, String pw, BufferedWriter bw)throws IOException{
        
    
        if(mapa.containsKey(id))
            {
                System.out.println("\nError\nNo se puede registrar de nuevo el mismo Administrador\n"); 
            }

        else{   
            
            mapa.put(id, pw);  
                System.out.println("\nAdministrador registrado con exito");
            }   
        
            Iterator<String> iterator = mapa.keySet().iterator();
                String inputFilename = "C:\\Users\\aldop\\Desktop\\8 sem\\Taller de productividad basada en herramientas tecnológicas\\Inventario\\com.mycompany_Inventario\\src\\main\\java\\proyecto\\inventario\\Administradores.csv"; 

                BufferedWriter bufferedWriter = null;

                try {
                    bufferedWriter = new BufferedWriter(new FileWriter(inputFilename));

                   while(iterator.hasNext())                                
                    {
                    String llave = iterator.next();                      


                    bufferedWriter.write(llave+","+mapa.get(llave)+"\n");   
                    }

                    }
                 catch(IOException e) {
                    System.out.println("IOException catched while writing: " + e.getMessage());
                    } finally {
                        try {
                            if (bufferedWriter != null) {
                                bufferedWriter.close();
                                System.out.println("\nCambios guardados");
                            }
                        } catch (IOException e) {
                            System.out.println("IOException catched while closing: " + e.getMessage());
                        }
                    }
        
    }
   
    

   public static boolean contrasena(HashMap <String,String> mapa, String id, String pw)
    {
       
       boolean i ;

            if(mapa.containsKey(id))
            {
                if(mapa.get(id).equals(pw))
                i = true;
                
                else
                {
                    System.out.println("Contraseña incorrecta!\n");
                
                    i = false;
                }
            }
       
            else
            {
                System.out.println("Administrador existente\n");
                i = false;
            }
    
        return i;
    }
   

   public static void load(HashMap<String, String> m)
    {
        String inputFilename = "C:\\Users\\aldop\\Desktop\\8 sem\\Taller de productividad basada en herramientas tecnológicas\\Inventario\\com.mycompany_Inventario\\src\\main\\java\\proyecto\\inventario\\Administradores.csv";
        String a [];                
        
        BufferedReader bufferedReader = null;

        try {
            bufferedReader = new BufferedReader(new FileReader(inputFilename));

            String line;
            while ((line = bufferedReader.readLine()) != null) {
                a = line.split(",");        
                m.put(a[0],a[1]);           
            }
        } catch(IOException e) {
            System.out.println("IOException catched while reading: " + e.getMessage());
        } finally {
            try {
                if (bufferedReader != null) {
                    bufferedReader.close();
                }
            } catch (IOException e) {
                System.out.println("IOException catched while closing: " + e.getMessage());
            }
        }

    }
   

   
   
   
   
}
