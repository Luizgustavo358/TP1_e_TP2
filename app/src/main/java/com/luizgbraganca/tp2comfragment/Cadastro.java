package com.luizgbraganca.tp2comfragment;


import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Vibrator;
import android.provider.CalendarContract;
import android.provider.ContactsContract;
import android.support.design.widget.TextInputLayout;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import java.util.Calendar;


/**
 * A simple {@link Fragment} subclass.
 */
public class Cadastro extends Fragment
{
    public View view;

    public Cadastro() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        view = inflater.inflate(R.layout.fragment_cadastro, container, false);

        setBotoes( );

        return(view);
    }

    /**
     * Metodo para converter EditText em String.
     * @param texto
     * @return
     */
    public String converteTexto(EditText texto)
    {
        // definir dados
        String result = texto.getText().toString();

        return(result);
    }// end converteTexto( )

    /**
     * Metodo para verificar cada botao.
     */
    private void setBotoes( )
    {
        // definindo botoes
        Button addContato  = (Button) view.findViewById(R.id.addContato),
                pesquisa    = (Button) view.findViewById(R.id.pesqEnd),
                salvaAniv   = (Button) view.findViewById(R.id.anivesario),
                envWhatsapp = (Button) view.findViewById(R.id.whatsapp);

        // definindo os editText
        final EditText nome     = (EditText) view.findViewById(R.id.nome),
                nasc     = (EditText) view.findViewById(R.id.nascimento),
                tel      = (EditText) view.findViewById(R.id.phone),
                email    = (EditText) view.findViewById(R.id.email),
                endereco = (EditText) view.findViewById(R.id.endereco);

        final TextInputLayout nomeInput  = (TextInputLayout) view.findViewById(R.id.input_layout_name),
                nascInput  = (TextInputLayout) view.findViewById(R.id.input_layout_nasc),
                telInput   = (TextInputLayout) view.findViewById(R.id.input_layout_tel),
                emailInput = (TextInputLayout) view.findViewById(R.id.input_layout_email),
                endInput   = (TextInputLayout) view.findViewById(R.id.input_layout_endereco);

        // botao de adicionar contato
        addContato.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v)
            {
                boolean emptyNome = TextUtils.isEmpty(converteTexto(nome)),
                        emptyEmail = TextUtils.isEmpty(converteTexto(email)),
                        emptytel = TextUtils.isEmpty(converteTexto(tel)),
                        emptyAddress = TextUtils.isEmpty(converteTexto(endereco));

                if(emptyNome)
                {
                    //vib.vibrate(120);
                    nomeInput.setErrorEnabled(true);
                    nomeInput.setError(getResources().getString(R.string.erro_EditText_Nome));
                }// end if

                if(emptyEmail)
                {
                    //vib.vibrate(120);
                    emailInput.setErrorEnabled(true);
                    emailInput.setError(getResources().getString(R.string.erro_EditText_Email));
                }// end if

                if(emptytel)
                {
                    //vib.vibrate(120);
                    telInput.setErrorEnabled(true);
                    telInput.setError(getResources().getString(R.string.erro_EditText_Tel));
                }// end if

                if(emptyAddress)
                {
                    //vib.vibrate(120);
                    endInput.setErrorEnabled(true);
                    endInput.setError(getResources().getString(R.string.erro_EditText_Endereco));
                }// end if

                if(!emptyNome && !emptyEmail && !emptytel && !emptyAddress)
                {
                    nomeInput.setErrorEnabled(false);
                    emailInput.setErrorEnabled(false);
                    telInput.setErrorEnabled(false);
                    endInput.setErrorEnabled(false);
                    addContact(converteTexto(nome), converteTexto(email), converteTexto(tel), converteTexto(endereco));
                }// end if
            }// end onClick( )
        });

        // botao de pesquisa de endereco
        pesquisa.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v)
            {
                boolean emptyEndereco = TextUtils.isEmpty(converteTexto(endereco));

                if(emptyEndereco)
                {
                    //vib.vibrate(120);
                    endInput.setErrorEnabled(true);
                    endInput.setError(getResources().getString(R.string.erro_EditText_Endereco));
                }// end if

                if(!emptyEndereco)
                {
                    endInput.setErrorEnabled(false);
                    mapa(converteTexto(endereco));
                }// end if
            }// end onClick
        });

        // botao de salvar aniversario
        salvaAniv.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v)
            {
                boolean emptyNome = TextUtils.isEmpty(converteTexto(nome)),
                        emptyData = TextUtils.isEmpty(converteTexto(nasc));

                if(emptyNome)
                {
                    //vib.vibrate(120);
                    nomeInput.setErrorEnabled(true);
                    nomeInput.setError(getResources().getString(R.string.erro_EditText_Nome));
                }// end if

                if(emptyData)
                {
                    //vib.vibrate(120);
                    nascInput.setErrorEnabled(true);
                    nascInput.setError(getResources().getString(R.string.erro_EditText_Nasc));
                }// end if

                if(!emptyNome && !emptyData)
                {
                    nomeInput.setErrorEnabled(false);
                    nascInput.setErrorEnabled(false);
                    aniversario(converteTexto(nome), converteTexto(nasc));
                }// end if
            }// end onClick
        });

        // botao de enviar whatsapp
        envWhatsapp.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v)
            {
                boolean emptyTel = TextUtils.isEmpty(converteTexto(tel));

                if(emptyTel)
                {
                    //vib.vibrate(120);
                    telInput.setErrorEnabled(true);
                    telInput.setError(getResources().getString(R.string.erro_EditText_Tel));
                }// end if

                if(!emptyTel)
                {
                    telInput.setErrorEnabled(false);
                    whatsapp(converteTexto(tel));
                }// end if
            }// end onClick
        });
    }// end setBotoes( )

    /**
     * Metodo addContact( ) - adiciona um contato.
     */
    public void addContact(String nome, String email, String tel, String end)
    {
        // criando a intent
        Intent intent = new Intent(ContactsContract.Intents.Insert.ACTION);
        intent.setType(ContactsContract.RawContacts.CONTENT_TYPE);

        // mandando o nome
        intent.putExtra(ContactsContract.Intents.Insert.NAME, nome);

        // mandando email
        intent.putExtra(ContactsContract.Intents.Insert.EMAIL, email);
        intent.putExtra(ContactsContract.Intents.Insert.EMAIL_TYPE, ContactsContract.CommonDataKinds.Email.TYPE_HOME);

        // mandando o telefone
        intent.putExtra(ContactsContract.Intents.Insert.PHONE, tel);
        intent.putExtra(ContactsContract.Intents.Insert.PHONE_TYPE, ContactsContract.CommonDataKinds.Phone.TYPE_HOME);

        // mandando o endereco
        intent.putExtra(ContactsContract.Intents.Insert.POSTAL, end);
        intent.putExtra(ContactsContract.Intents.Insert.POSTAL_TYPE, ContactsContract.CommonDataKinds.StructuredPostal.TYPE_HOME);

        // iniciando a intent
        startActivity(intent);
    }// end addContact( )

    /**
     * Metodo que chama a intent do Mapa.
     */
    public void mapa(String address)
    {
        // intent do google maps
        Uri mapUri = Uri.parse("geo:0,0?q=" + Uri.encode(address));
        Intent mapIntent = new Intent(Intent.ACTION_VIEW, mapUri);
        mapIntent.setPackage("com.google.android.apps.maps");

        // iniciando activity
        startActivity(mapIntent);
    }// end mapa( )

    /**
     * Metodo para adicionar data de aniversário na agenda.
     */
    public void aniversario(String nome, String nasc)
    {
        // definir dados
        int dia, mes;
        Intent intent  = new Intent(Intent.ACTION_INSERT);

        // pegando dia
        dia = getDia(nasc);

        // pegando mes
        mes = getMes(nasc);

        // adicionando setando a data
        intent.setData(CalendarContract.Events.CONTENT_URI);

        // adicionando titulo do evento
        intent.putExtra(CalendarContract.Events.TITLE, "Aniversário de " + nome);

        // adicionando que o evento dura o dia todo
        intent.putExtra(CalendarContract.EXTRA_EVENT_ALL_DAY, true);

        // pega o dia que comeca
        Calendar startTime = Calendar.getInstance( );
        startTime.set(2017, mes, dia, 0, 0);

        intent.putExtra(CalendarContract.EXTRA_EVENT_BEGIN_TIME, startTime.getTimeInMillis( ));
        intent.putExtra(CalendarContract.EXTRA_EVENT_END_TIME, startTime.getTimeInMillis( ));
        intent.putExtra("rrule", "FREQ=YEARLY");

        // iniciando a activity
        startActivity(intent);
    }// end aniversario( )

    /**
     * Metodo getDia( ) - pega o dia do aniversario
     * @param aniversario
     * @return
     */
    public int getDia(String aniversario)
    {
        // definir dados
        int dia;

        // XX/XX/XXXX
        dia = Integer.parseInt(aniversario.substring(0, 2));

        return(dia);
    }// end getDia( )

    /**
     * Metodo getMes( ) - pega o mes do aniversario
     * @param aniversario
     * @return mes
     */
    public int getMes(String aniversario)
    {
        // definir dados
        int mes;

        // XX/XX/XXXX
        mes = Integer.parseInt(aniversario.substring(3, 5));

        // decrementando
        mes = mes - 1;

        return(mes);
    }// end getMes( )

    /**
     * Metodo que manda mensagem pelo whatsapp.
     */
    public void whatsapp(String number)
    {
        // passa o numero do contato
        Uri uri = Uri.parse("smsto:" + number);

        // criando a intent
        Intent sendIntent = new Intent(Intent.ACTION_SENDTO, uri);

        // tipo texto
        sendIntent.setPackage("text/plain");

        // adiciona o package do whatsapp
        sendIntent.setPackage("com.whatsapp");

        // iniciando activity
        startActivity(Intent.createChooser(sendIntent, ""));
    }// end whatsapp( )
}
