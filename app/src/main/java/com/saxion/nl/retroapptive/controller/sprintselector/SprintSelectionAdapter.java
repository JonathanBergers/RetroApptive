package com.saxion.nl.retroapptive.controller.sprintselector;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.saxion.nl.retroapptive.R;

import org.w3c.dom.Text;

import java.util.List;

/**
 * Created by Thomas on 3-6-2015.
 */
public class SprintSelectionAdapter extends ArrayAdapter<Item> {

    private LayoutInflater mInflater;

    public SprintSelectionAdapter(Context context, List<Item> items) {
        super(context, 0, items);
        mInflater = LayoutInflater.from(context);
    }

    @Override
    public int getViewTypeCount() {
        return 1;//project en sprint
    }

    @Override
    public int getItemViewType(int position) {
        return getItem(position).getViewType();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder = null;
        int rowType = getItemViewType(position);
        View View;
        if (convertView == null) {
            holder = new ViewHolder();
            switch (rowType) {
                case ProjectItem.VIEW_TYPE:
                    final ProjectItem projectItem = (ProjectItem) getItem(position);
                    convertView = mInflater.inflate(android.R.layout.simple_list_item_activated_1, null);
                    TextView projectTextView = (TextView) convertView.findViewById(android.R.id.text1);
                    projectTextView.setText(projectItem.getProject().getName());
                    break;
                case SprintItem.VIEW_TYPE:
                    final SprintItem sprintItem = (SprintItem) getItem(position);
                    convertView = mInflater.inflate(android.R.layout.simple_list_item_activated_1, null);
                    TextView sprintTextView = (TextView) convertView.findViewById(android.R.id.text1);
                    sprintTextView.setText("    Sprint " + sprintItem.getSprint().getSprintID());
                    break;
            }
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        return convertView;
    }

    public static class ViewHolder {
        public View View;
    }
}

