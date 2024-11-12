/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */
package model;

import java.io.IOException;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Before;

/**
 *
 * @author Nicolle
 */
public class PersistenceCSVImplTest {
    
    PersistenceCSVImpl persistenceCSVImpl;
    
    @Before
    public void setUp() {
        persistenceCSVImpl = PersistenceCSVImpl.getPersistenceCSVImpl();
    }
    
    @Test
    public void testCreateFile() throws IOException {
        
        // exemplo de como criar arquivo dentro da pasta de teste
        persistenceCSVImpl.createFile("./test/files/teste.csv");
    }
    
    @Test
    public void testSaveRegister() throws IOException {
    }
    
    @Test
    public void testListRegisterByType() throws IOException {
    }
}
