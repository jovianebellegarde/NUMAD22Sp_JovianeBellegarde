package edu.neu.numad22sp_jovianebellegarde;

import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class LinkViewAdapter extends RecyclerView.Adapter<LinkViewAdapter.LinkViewHolder> {
  private final List<Link> cardList = new ArrayList<>();
  private LinkClickListener clickListener;

  public static class LinkViewHolder extends RecyclerView.ViewHolder {
    public EditText name;
    public EditText url;

    public LinkViewHolder(View linkView, final LinkClickListener clickListener) {
      super(linkView);
      this.name = linkView.findViewById(R.id.link_name);
      this.url = linkView.findViewById(R.id.link_url);
      linkView.setOnClickListener(v -> {
        if (clickListener == null) {
          return;
        }
        int linkPosition = getLayoutPosition();
        if (linkPosition != RecyclerView.NO_POSITION) {
          clickListener.onLinkClick(linkPosition);
        }
      });
    }
  }
  @NonNull
  @Override
  public LinkViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
    return new LinkViewHolder(LayoutInflater
            .from(parent.getContext())
            .inflate(R.layout.link_card, parent, false), clickListener);
  }

  @Override
  public void onBindViewHolder(@NonNull LinkViewHolder holder, int position) {

  }

  @Override
  public int getItemCount() {
    return this.cardList.size();
  }
}
