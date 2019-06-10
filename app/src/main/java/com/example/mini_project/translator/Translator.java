/*
 * Copyright (c) 2019, Erick Ogaro.
 */


package com.example.mini_project.translator;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import android.support.v7.widget.RecyclerView;

import com.example.mini_project.R;

import java.util.List;


public class Translator extends RecyclerView.Adapter<Translator.ViewHolder> {
    private Context context;
    private List<String> word_list;

    public Translator(Context context, List<String> word_list) {
        this.context = context;
        this.word_list = word_list;
    }

    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewtype) {
        View view = LayoutInflater.from(context).inflate(R.layout.translations, viewGroup, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        if (word_list.isEmpty()) {
            viewHolder.words.setText("No translations found");
        } else if (!word_list.isEmpty()) {
            viewHolder.words.setText(word_list.get(i));
        }

    }

    @Override
    public int getItemCount() {
        return word_list.size();
    }

    public void addList(List<String> wordlist) {
        this.word_list = wordlist;
        notifyDataSetChanged();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView words;

        public ViewHolder(@NonNull View item) {
            super(item);

            words = item.findViewById(R.id.list_of_words);
        }
    }
}
