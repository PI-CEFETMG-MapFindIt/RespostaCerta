/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.cefetmg.respostaCerta.util;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.awt.image.RenderedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import javax.imageio.ImageIO;


/**
 *
 * @author Pós Graduação
 */
public class Converter {
    public static Byte[] ImageToByteArray(Image image) {
        if(image == null){
            return null;
        }
        BufferedImage bi = new BufferedImage( image.getWidth(null), image.getHeight(null), BufferedImage.TYPE_INT_RGB );
        Graphics2D g2 = bi.createGraphics();
        g2.drawImage( image, 0, 0, null ); 
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        try {
            ImageIO.write((RenderedImage) image, "jpg", baos );
        } catch (IOException ex) {
            System.out.println("Erro ao converter imagem");
        }
        byte[] data = baos.toByteArray();
        return Converter.ByteArrayToObject(data);
    }
    
    public static Image ByteArrayToImage(Byte[] byteArrayObj){
        if(byteArrayObj == null){
            return null;
        }
        byte[] byteArray = Converter.ByteArrayToPrimitive(byteArrayObj);
        BufferedImage bImageFromConvert = null;
        try {
            InputStream in = new ByteArrayInputStream(byteArray);
            bImageFromConvert = ImageIO.read(in);
        } catch (IOException ex) {
            System.out.println("Erro ao converter imagem");
        }
        return bImageFromConvert;
    }
    
    public static Byte[] ByteArrayToObject(byte[] array) {
        if (array == null) {
            return null;
        } else if (array.length == 0) {
            return new Byte[0];
        }
        final Byte[] result = new Byte[array.length];
        for (int i = 0; i < array.length; i++) {
            result[i] = new Byte(array[i]);
        }
        return result;
    }
    
    public static byte[] ByteArrayToPrimitive(Byte[] array) {
        if (array == null) {
            return null;
        } else if (array.length == 0) {
            return new byte[0];
        }
        final byte[] result = new byte[array.length];
        for(int i = 0; i < array.length; i++){
            result[i] = array[i];
        }
        return result;
    }
}
