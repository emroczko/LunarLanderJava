package company;

import java.io.*;
import java.net.Socket;
import java.net.UnknownHostException;

/**
 * Klasa odpowiedzialna za kontakt z serwerem. Wysyła zapytania o udostępniennie danych konfiguracyjnych i rankingu
 */
public class Client {

    private static Socket socket;
<<<<<<< HEAD
    /**Zmienna przechowująca adres ip serwera*/
=======
>>>>>>> 505455e143644beeb11f1fc8bd94f4c3d40c1f14
    static String Address;
    /**Zmienna przechowująca numer portu serwera*/
    static int Port;
<<<<<<< HEAD
    /**Zmienna określająca czy jesteśmy online*/
    static boolean online = false;

    /**
     * Tworzy łącze z serwerem, tworzy obiekt klasy socket.
     * @param address adres ip serwera
     * @param port numer portu serwera
     * @throws UnknownHostException
     * @throws IOException
     */
    static void Connect(String address, int port) throws UnknownHostException, IOException {
        socket = new Socket(address, port);
        Address = address;
        Port = port;
        System.out.println("Connected");
=======
    static boolean online;
>>>>>>> 505455e143644beeb11f1fc8bd94f4c3d40c1f14

    static void Connect(String address, int port) throws UnknownHostException, IOException {
        socket = new Socket(address, port);
        Address = address;
        Port = port;
        System.out.println("Connected");
    }

    /**
     * Wysyła zapytania do serwera
     * @param command komenda odpowiadająca zapytaniu które chcemy wysłać do serwera
     * @return zwraca odpowiedź od serwera
     * @throws IOException
     */
    static String getProperty(String command) throws IOException {
        try {
            socket = new Socket(Address, Port);
        }
        catch(Exception e){e.printStackTrace();}
        PrintWriter pw = new PrintWriter(socket.getOutputStream(), true);
        pw.println(command);
        InputStream in = socket.getInputStream();
        BufferedReader buf = new BufferedReader(new InputStreamReader(in));
        return buf.readLine();
    }

    /**
     * Wysyła do serwera zapytanie o dane konfiguracyjne przy uzyciu metody getProperty z odpowiednią komendą
     * @return zwraca dane konfiguracyjne pobrane z serwera
     * @throws IOException
     */
    static String getConfigs() throws IOException{
        String configs = getProperty("GetConfigs");
        socket.close();
        return configs;
    }

    /**
     * Wysyła do serwera zapytanie o dane konfiguracyjne przy uzyciu metody getProperty z odpowiednią komendą
     * @param levelNumber numer poziomu którego dane konfiguracyjne chcemy otrzymać
     * @return zwraca dane konfiguracyjne poziomu
     * @throws IOException
     */
    static String getLevel(int levelNumber) throws IOException{
        String levelConfigs = getProperty("GetLevel-" + levelNumber);
        socket.close();
        return levelConfigs;
    }
    /**
     * Zapisuje wyniku na serwerze, w tym celu wywoluje metode connect z odpowiednim zapytaniem
     * @param nick nick gracza wraz z wynikiem odzielone znakiem "-"
     */
    public static void saveScore(String nick) throws IOException {
        getProperty("saveScore" + "-" + nick);
        socket.close();
    }
}
