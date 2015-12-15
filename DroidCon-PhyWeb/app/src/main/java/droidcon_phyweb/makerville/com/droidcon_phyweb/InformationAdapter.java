package droidcon_phyweb.makerville.com.droidcon_phyweb;

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

public class InformationAdapter extends RecyclerView.Adapter<InformationAdapter.ViewHolder> {

    List<Info> mItems;

    public InformationAdapter() {
        super();
        mItems = new ArrayList<Info>();
        Info ddi;
        ddi = new Info();
        ddi.setName("Bhushan Authankar");
        ddi.setLink("archbloom.github.io");
        ddi.setImg(R.drawable.bhushan);
        mItems.add(ddi);

        ddi = new Info();
        ddi.setName("Chinmay Kulkarni");
        ddi.setLink("chinmay1994.github.io");
        ddi.setImg(R.drawable.chinmay);
        mItems.add(ddi);

        ddi = new Info();
        ddi.setName("Sitaram Shelke");
        ddi.setLink("sitaramshelke.github.io");
        ddi.setImg(R.drawable.sitaram1);
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
        Info ddi = mItems.get(i);
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
        }
    }
}
