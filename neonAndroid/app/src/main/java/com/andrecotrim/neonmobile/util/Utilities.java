package com.andrecotrim.neonmobile.util;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;

public class Utilities {

    public static AlertDialog exibirMensagens (Context context, String titulo, String mensagem) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle(titulo);
        builder.setMessage(mensagem);
        builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        AlertDialog alerta = builder.create(); //Exibe
        return alerta;
    }

    public static String recortarNome(int size, String mensagem) {
        if (mensagem.length() > size) {
            return (mensagem.substring(0, size -3) + "...");
        }
        return mensagem;
    }

    public static String formatarTelefone (String telefone) {
        telefone = (telefone.length() < 7) ? "" : telefone;
        String s = "";
        switch (telefone.length()) {
            case 9:
                s = String.valueOf(telefone).replaceFirst("(\\d{5})(\\d+)", "$1-$2");
                break;
            case 8:
                s = String.valueOf(telefone).replaceFirst("(\\d{4})(\\d+)", "$1-$2");
                break;
            case 11:
                s = telefone.startsWith("0") ? String.valueOf(telefone).replaceFirst("(\\d{3})(\\d{4})(\\d+)", "$1 $2-$3")
                        : String.valueOf(telefone).replaceFirst("(\\d{2})(\\d{5})(\\d+)", "($1) $2-$3");
                break;
            case 10:
                s = String.valueOf(telefone).replaceFirst("(\\d{2})(\\d{4})(\\d+)", "($1) $2-$3");
                break;
            case 13:
                s = String.valueOf(telefone).replaceFirst("(\\d{3})(\\d{2})(\\d{4})(\\d+)", "$1 ($2) $3-$4");
                break;
            default:
                StringBuffer buffer = new StringBuffer(telefone);
                buffer.reverse();
                s = String.valueOf(buffer).replaceFirst("(\\d{4})(\\d{4})(\\d+)", "$1-$2 $3");
                buffer = new StringBuffer(s);
                buffer.reverse();
                s = buffer.toString();
        }
        return s;
    }

    /* BUILD GET PARAMETERS
        Uri.Builder builder = new Uri.Builder()
                .scheme("http")
                .encodedAuthority(ConstantsUtils.URL_SERVER)
                .appendPath(ConstantsUtils.ROUTE_GENERATE_TOKEN)
                .appendQueryParameter("nome", "André Cotrim")
                .appendQueryParameter("email", "cotrim.andre@gmail.com");
        String restResultURL = builder.build().toString();
    */

    /* Log printer
    Toast.makeText(this, "Query: " + query, Toast.LENGTH_LONG).show();
        Log.println(Log.ERROR, "error", query);*/

    // LOGIN AUTHENTICATION POST PARAMETERS */
    // String usuarioPostRequestJson = String.format("{ \"parametro1\" : \"Andre %s\" }", "parametro2 %s", "", "");



}
