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
 * Created by Chinmay on 12/12/15.
 */
public class PhysicalWebAdapter extends RecyclerView.Adapter<PhysicalWebAdapter.ViewHolder> {

    List<Info> list = new ArrayList<Info>();

    public PhysicalWebAdapter()
    {
        Info info = new Info();
        info.setName("Site");
        info.setLink("https://google.github.io/physical-web/");
        info.setImg(R.drawable.phywebblue);
        list.add(info);
        info = new Info();
        info.setName("App");
        info.setLink("https://play.google.com/store/apps/details?id=physical_web.org.physicalweb");
        info.setImg(R.drawable.phywebgreen);
        list.add(info);
        info = new Info();
        info.setName("Source");
        info.setLink("https://github.com/google/physical-web");
        info.setImg(R.drawable.phyweborange);
        list.add(info);
    }

    @Override
    public PhysicalWebAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.card_view, parent, false);
        ViewHolder viewHolder = new ViewHolder(v);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(PhysicalWebAdapter.ViewHolder holder, int position) {
        Info ddi = list.get(position);
        holder.name.setText(ddi.getName());
        holder.link.setText(ddi.getLink());
        holder.img.setImageResource(ddi.getImg());
    }

    @Override
    public int getItemCount() {
        return list.size();
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
