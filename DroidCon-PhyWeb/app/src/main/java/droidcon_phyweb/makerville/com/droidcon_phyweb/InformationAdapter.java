package droidcon_phyweb.makerville.com.droidcon_phyweb;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Archbloom on 12/8/2015.
 */
///this file is not much useful
public class InformationAdapter extends RecyclerView.Adapter<InformationAdapter.ViewHolder> {


    List<Dev_info> mItems;

    public InformationAdapter() {
        super();
        mItems = new ArrayList<Dev_info>();
        Dev_info ddi;
        ddi = new Dev_info();
        ddi.setName("Bhushan Authankar");
        ddi.setLink("archbloom.github.io");
        ddi.setImg(R.drawable.bhushan);
        mItems.add(ddi);

        ddi = new Dev_info();
        ddi.setName("Chinmay Kulkarni");
        ddi.setLink("chinmay1994.github.io");
        ddi.setImg(R.drawable.chinmay);
        mItems.add(ddi);

        ddi = new Dev_info();
        ddi.setName("Sitaram Shelke");
        ddi.setLink("sitaramshelke.github.io");
        ddi.setImg(R.drawable.sitaram);
        mItems.add(ddi);
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.card_view, viewGroup, false);
        ViewHolder viewHolder = new ViewHolder(v);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, int i) {
        Dev_info ddi = mItems.get(i);
        viewHolder.name.setText(ddi.getName());
        viewHolder.link.setText(ddi.getLink());
        viewHolder.img.setImageResource(ddi.getImg());
    }
    @Override
    public int getItemCount() {
        return mItems.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        public ImageView img;
        public TextView name;
        public TextView link;

        public ViewHolder(View itemView) {
            super(itemView);
            img = (ImageView)itemView.findViewById(R.id.person_photo);
            name = (TextView)itemView.findViewById(R.id.person_name);
            link = (TextView)itemView.findViewById(R.id.person_link);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent browserIntent;
//                    Toast.makeText(v.getContext(), "Position = " + getAdapterPosition(), Toast.LENGTH_SHORT).show();
                    int pos = getAdapterPosition();
                    if (pos == 0) {
                        browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://archbloom.github.io/"));
                        v.getContext().startActivity(browserIntent);
                    } else if (pos == 1) {
                        browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://chinmay1994.github.io/"));
                        v.getContext().startActivity(browserIntent);
                    }else if(pos == 2) {
                        browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://sitaramshelke.github.io/"));
                        v.getContext().startActivity(browserIntent);
                    }
                }
            });
        }
    }
}
