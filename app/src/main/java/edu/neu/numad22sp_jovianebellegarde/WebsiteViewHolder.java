package edu.neu.numad22sp_jovianebellegarde;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class WebsiteViewHolder extends RecyclerView.ViewHolder {
  private TextView name;
  private TextView link;


  public WebsiteViewHolder(@NonNull View itemView, TextView name, TextView link) {
    super(itemView);
    this.name = name;
    this.link = link;
  }
}
