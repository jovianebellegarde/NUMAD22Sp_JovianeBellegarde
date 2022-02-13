package edu.neu.numad22sp_jovianebellegarde;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class LinkViewHolder extends RecyclerView.ViewHolder {
  private TextView name;
  private TextView url;

  public LinkViewHolder(@NonNull View itemView) {
    super(itemView);
    this.name = itemView.findViewById(R.id.link_name);
    this.url = itemView.findViewById(R.id.link_url);

    itemView.setOnClickListener(new View.OnClickListener() {

      @Override
      public void onClick(View v) {

      }
    });
  }
}
