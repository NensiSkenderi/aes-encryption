package com.aes.controllers;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

import javafx.fxml.Initializable;
import org.apache.commons.codec.binary.Base64;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;

public class EncryptDecryptController implements Initializable {

    @FXML
    public Button reset, btn_e, btn_d;
    @FXML
    private TextField txt_string_e, e, txt_string_d, d;
    @FXML
    private Label lblNote;

    String key = "Tst12345Tst12345";
    String initVector = "RandomInitVector";


    public void initialize(URL location, ResourceBundle resources) {

    }

    @FXML
    public void encrypt() {
        e.setText(encrypt(key, initVector, txt_string_e.getText()));
    }

    @FXML
    public void decrypt() {
        d.setText(decrypt(key, initVector, txt_string_d.getText()));
    }

    public static String encrypt(String key, String initVector, String value) {
        try {
            IvParameterSpec iv = new IvParameterSpec(initVector.getBytes("UTF-8"));
            SecretKeySpec skeySpec = new SecretKeySpec(key.getBytes("UTF-8"), "AES");

            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5PADDING");
            cipher.init(1, skeySpec, iv);

            byte[] encrypted = cipher.doFinal(value.getBytes());

            return Base64.encodeBase64String(encrypted);
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return null;
    }

    @FXML
    public void reset() {
        txt_string_d.setText("");
        txt_string_e.setText("");
        e.setText("");
        d.setText("");
    }

    public static String decrypt(String key, String initVector, String encrypted) {
        try {
            IvParameterSpec iv = new IvParameterSpec(initVector.getBytes("UTF-8"));
            SecretKeySpec skeySpec = new SecretKeySpec(key.getBytes("UTF-8"), "AES");

            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5PADDING");
            cipher.init(2, skeySpec, iv);

            byte[] original = cipher.doFinal(Base64.decodeBase64(encrypted));

            return new String(original);
        } catch (Exception ex) {
            Alert a = new Alert(AlertType.ERROR, "Input length must be multiple of 16", ButtonType.CANCEL);
            a.show();
        }

        return null;
    }

}
		

