package josenavarro.cbaapp.database;

import com.google.firebase.database.FirebaseDatabase;

/**
 * Created by José on 22/12/2016.
 */

public class FirebaseConPersistencia {
    private static FirebaseConPersistencia instancia = null;
    private static FirebaseDatabase baseDatosPersistencia;

    protected FirebaseConPersistencia(){
        baseDatosPersistencia = FirebaseDatabase.getInstance();
        baseDatosPersistencia.setPersistenceEnabled(true);
    }

    public static FirebaseConPersistencia getInstancia(){
        if(instancia == null){
            instancia = new FirebaseConPersistencia();
        }
        return instancia;
    }
}
