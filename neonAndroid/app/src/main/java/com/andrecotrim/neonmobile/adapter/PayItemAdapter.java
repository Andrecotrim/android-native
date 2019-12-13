package com.andrecotrim.neonmobile.adapter;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.andrecotrim.neonmobile.R;
import com.andrecotrim.neonmobile.activity.PaymentCandidatesActivity;
import com.andrecotrim.neonmobile.activity.SendMoneyActivity;
import com.andrecotrim.neonmobile.util.Utilities;
import com.andrecotrim.neonmobile.vo.Contact;

import java.util.List;

public class PayItemAdapter extends BaseAdapter {

    private Context context;
    private LayoutInflater mInflater;
    private List<Contact> itens;

    public PayItemAdapter(Context context, List<Contact> itens) {
        this.context = context;
        this.itens = itens;
        mInflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return itens.size();
    }

    @Override
    public Object getItem(int position) {
        return itens.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    public View getView(final int position, View view, ViewGroup parent) {
        ItemSuporteList itemHolder;
        if (view == null) {
            view = mInflater.inflate(R.layout.contact_item, null);
            itemHolder = new ItemSuporteList();
            itemHolder.txtNome = ((TextView) view.findViewById(R.id.nome));
            itemHolder.txtTelefone = ((TextView) view.findViewById(R.id.telefone));
            view.setTag(itemHolder);
        } else {
            itemHolder = (ItemSuporteList) view.getTag();
        }
        final Contact item = itens.get(position);
        itemHolder.txtNome.setText(Utilities.recortarNome(32, item.getNome()));
        itemHolder.txtTelefone.setText(Utilities.formatarTelefone(item.getTelefone()));

        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context, "Click aqui", Toast.LENGTH_LONG).show();

                Intent intent = new Intent(context, SendMoneyActivity.class);
                intent.putExtra("CANDIDATO", item);
                (context).startActivity(intent);

                /*Service service = (Service) getItem(position);
                Intent intent = new Intent(context, ServiceFormActivity.class);
                intent.putExtra("servico", service);
                ((ServiceListActivity)context).startActivityForResult(intent, 1);*/
            }
        });


        /*((ImageButton) view.findViewById(R.id.deleteId)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Service service = (Service) getItem(position);
                DialogInterface.OnClickListener sucess = new DialogInterface.OnClickListener() {

                    public void onClick(DialogInterface arg0, int arg1) {
                        String msg = "";
                        if (service.getId() == 1L) {
                            msg = "Serviço padrão não pode ser excluído";
                        } else {
                            boolean retorno = new ServiceDao(context).excluirServicos(service.getId());
                            if (retorno) {
                                msg = "Serviço excluído com sucesso!";
                                ((ServiceListActivity)context).listarServicos();
                            } else {
                                msg = "Falha ao excluir serviço, tente novamente.";
                            }
                        }
                        Toast.makeText(context, msg, Toast.LENGTH_SHORT).show();
                    }
                };

                DialogInterface.OnClickListener cancel = new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface arg0, int arg1) {
                    }
                };
                String msg = String.format("Deseja excluir Serviço?");
                Utilities.exibirMensagens(context,
                        "EXCLUIR", msg,
                        "Excluir", sucess,
                        "cancelar", cancel).show();
            }
        });*/
        return view;
    }

    private class ItemSuporteList {
        ImageView imgIcon;
        TextView txtNome;
        TextView txtTelefone;
    }
}
