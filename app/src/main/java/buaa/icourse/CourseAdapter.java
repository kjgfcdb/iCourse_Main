package buaa.icourse;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;


import org.json.JSONArray;
import org.json.JSONObject;
import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import static java.lang.Math.min;

public class CourseAdapter extends RecyclerView.Adapter<CourseAdapter.ViewHolder> {
    private Context mContext;
    private List<CourseItem> mCourseList;
    public static final String TAG = "CourseAAAAdapter";

    static class ViewHolder extends RecyclerView.ViewHolder {
        LinearLayout layout;
        //资源图片以及资源名
        ImageView courseImage;
        TextView courseName;
        TextView courseTeacherName;
        TextView courseCredit;
        TextView courseType;

        ViewHolder(View view) {
            super(view);
            layout = (LinearLayout) view;
            courseImage = view.findViewById(R.id.course_image);
            courseName = view.findViewById(R.id.course_item_name);
            courseTeacherName = view.findViewById(R.id.course_item_teacherName);
            courseCredit = view.findViewById(R.id.course_item_credit);
            courseType = view.findViewById(R.id.course_item_type);
        }
    }

    CourseAdapter(List<CourseItem> CourseItemList) {
        mCourseList = CourseItemList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        final String courseName, courseCode = "";
        if (mContext == null) {
            mContext = parent.getContext();
        }
        final View view = LayoutInflater.from(mContext).inflate(R.layout.course_item,
                parent, false);
        final ViewHolder holder = new ViewHolder(view);
        holder.layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int position = holder.getAdapterPosition();
                CourseItem item = mCourseList.get(position);
                String courseName = item.getCourseName();
                int courseType = item.getCollegeId();
                int collegeId = item.getCollegeId();
                String courseCode = item.getCourseCode();

                Intent intent = new Intent(mContext, CourseActivity.class);
                intent.putExtra("Course_code", courseCode);
                intent.putExtra("Course_name", courseName);
                String courseCollege = (String)UserFragment.mp_college.get(collegeId);
                intent.putExtra("Course_college", courseCollege);

                mContext.startActivity(intent);
            }
        });
        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        CourseItem courseItem = mCourseList.get(position);
        holder.courseName.setText(courseItem.getCourseName());
        try{
            if (courseItem != null)
                holder.courseImage.setImageResource((int) MainActivity.collegePics.get(courseItem.getCollegeId()));
        } catch (Exception e) {
            holder.courseImage.setImageResource((int) MainActivity.collegePics.get(0));
        }
        holder.courseTeacherName.setText(courseItem.getTeacherName());
        holder.courseCredit.setText(courseItem.getCredit());
        holder.courseType.setText(courseItem.getCourseType());
    }

    @Override
    public int getItemCount() {
        return mCourseList.size();
    }
}
