package com.elanilsondejesus.instagram.alteracao.activity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.elanilsondejesus.instagram.R;

import java.util.List;

public class CorAdapter extends RecyclerView.Adapter<CorAdapter.ViewHolder> {
    private static List<Cor> listaCores;
    public CorAdapter(List<Cor> dados) {
        this.listaCores = dados;
    }

    public class ViewHolder extends RecyclerView.ViewHolder
            implements View.OnClickListener {

        public TextView txtCor, energetico,proteina,fibra,sodio,gordura,carboidrato;
        private TextView  valorDiarioenergetico,valorDiarioproteina,valorDiariofibra,valorDiariosodio,valorDiariogordura,valorDiariocarboidrato;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            txtCor = itemView.findViewById(R.id.txt_cor);
            energetico = itemView.findViewById(R.id.txt_energetico);
            proteina = itemView.findViewById(R.id.txt_proteina);
            fibra = itemView.findViewById(R.id.txt_fibra);
            sodio = itemView.findViewById(R.id.txt_sodio);
            gordura = itemView.findViewById(R.id.txt_gordura);
            carboidrato = itemView.findViewById(R.id.txt_carboidrato);
            valorDiarioenergetico = itemView.findViewById(R.id.textenergeticoValorDiario);
            valorDiarioproteina = itemView.findViewById(R.id.textProteinaValorDiario);
            valorDiariofibra = itemView.findViewById(R.id.textFibraValorDiario);
            valorDiariosodio = itemView.findViewById(R.id.textSodioValorDiario);
            valorDiariogordura = itemView.findViewById(R.id.textGorduraValorDiario);
            valorDiariocarboidrato = itemView.findViewById(R.id.textCarboidratoValorDiario);
            itemView.setOnClickListener(this);
        }
        @Override
        public void onClick(final View v) {
            // pegar o item clicado:
            final int idItemClicado = getAdapterPosition();
            final Cor corClicada = listaCores.get(idItemClicado);
            // criar o AlertDialog
            final AlertDialog.Builder builder = new AlertDialog.Builder(v.getContext());
            builder.setTitle("Opções");
            builder.setItems(R.array.opcoes_dialogo_bd,
                    new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int posicao) {
                            switch (posicao) {
                                case 0: // compartilhamento
                                    Intent i = new Intent(Intent.ACTION_SEND);
                                    String msg = "Selecionei a cor " + corClicada.getNome();
                                    i.setType("text/plain");
                                    i.putExtra(Intent.EXTRA_SUBJECT, "CorApp");
                                    i.putExtra(Intent.EXTRA_TEXT, msg);
                                    v.getContext().startActivity(
                                            Intent.createChooser(i, "Compartilhar...") );
                                    break;
                                case 1: // editar
                                    Intent j = new Intent(v.getContext(), InserirCorActivity.class);
                                    j.putExtra(CorBD.TabCor.COL_NOME, corClicada.getNome());
                                    j.putExtra(CorBD.TabCor.COL_ID, corClicada.getCod());
                                    v.getContext().startActivity( j );
                                    break;
                                case 2: // remover
                                    AlertDialog.Builder confirmacao = new AlertDialog.Builder(v.getContext());
                                    confirmacao.setMessage("Confirma a remoção da cor "+corClicada.getNome()+"?");
                                    confirmacao.setPositiveButton("Sim", new DialogInterface.OnClickListener() {
                                        @Override
                                        public void onClick(DialogInterface dialog, int which) {
                                            SQLiteDatabase bd = null;
                                            try {
                                                CorBD corBD = new CorBD(v.getContext());
                                                bd = corBD.getWritableDatabase();
                                                bd.delete(CorBD.TabCor.TABELA,
                                                        CorBD.TabCor.COL_ID + " = ? ",
                                                        new String[]{corClicada.getCod() + ""});
                                                atualizarPosRemocao( idItemClicado );
                                                Toast.makeText(v.getContext(), "Dado removido.",
                                                        Toast.LENGTH_SHORT).show();
                                            } catch (Exception e) {
                                                Toast.makeText(v.getContext(),
                                                        "Erro! Não foi possível remover o dado.",
                                                        Toast.LENGTH_SHORT).show();
                                            } finally {
                                                if(bd != null) bd.close();
                                            }
                                        }
                                    });
                                    confirmacao.setNegativeButton("Não", new DialogInterface.OnClickListener() {
                                        @Override
                                        public void onClick(DialogInterface dialog, int which) {
                                            dialog.dismiss();
                                        }
                                    });
                                    AlertDialog dialogRemocao = confirmacao.create();
                                    dialogRemocao.show();
                                    break;
                                case 3: // novo
                                    Intent k = new Intent(v.getContext(), InserirCorActivity.class);
                                    v.getContext().startActivity( k );
                                    break;
                            }
                        }
                    });
            AlertDialog dialog = builder.create();
            dialog.show();
        }

    }

    private void atualizarPosRemocao(int itemClicado) {
        listaCores.remove(itemClicado);
        notifyItemRemoved(itemClicado);
        notifyItemChanged(itemClicado, getItemCount()-1);
    }

    @Override
    public CorAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).
                inflate(R.layout.item_cor, parent, false);
        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.txtCor.setText(listaCores.get(position).getNome());
        holder.energetico.setText(listaCores.get(position).getEnergetico()+" Kcal");
        holder.proteina.setText(listaCores.get(position).getProteina()+" g");
        holder.gordura.setText(listaCores.get(position).getGordura()+" g");
        holder.fibra.setText(listaCores.get(position).getFibra()+" g");
        holder.sodio.setText(listaCores.get(position).getSodio()+" mg");
        holder.carboidrato.setText(listaCores.get(position).getCaboidrato()+" g");
        holder.valorDiarioenergetico.setText(listaCores.get(position).getEnergeticoValorDiario()+" %");
        holder.valorDiarioproteina.setText(listaCores.get(position).getProteinaValorDiario()+" %");
        holder.valorDiariogordura.setText(listaCores.get(position).getGorduraValorDiario()+" %");
        holder.valorDiariofibra.setText(listaCores.get(position).getFibraValorDiario()+" %");
        holder.valorDiariosodio.setText(listaCores.get(position).getSodioValorDiario()+" %");
        holder.valorDiariocarboidrato.setText(listaCores.get(position).getCaboidratoValorDiario()+" %");
    }

    @Override
    public int getItemCount() {
        if(listaCores != null)
            return listaCores.size();
        return 0;
    }
}