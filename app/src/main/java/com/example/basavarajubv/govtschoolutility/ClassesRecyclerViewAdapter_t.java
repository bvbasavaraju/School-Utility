package com.example.basavarajubv.govtschoolutility;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public class ClassesRecyclerViewAdapter_t extends RecyclerView.Adapter<ClassesRecyclerViewAdapter_t.ClassCardViewHolder_t>
{
  private Context context;
  private List<Class_t> classes;
  private OnItemClickListener onItemClickListener;

  public ClassesRecyclerViewAdapter_t(Context context_, List<Class_t> classes_)
  {
    this.context = context_;
    this.classes = classes_;
  }

  public interface OnItemClickListener
  {
    void OnItemClick(Class_t class_);
  }

  public void SetOnItemClickListener(OnItemClickListener onItemClickListener_)
  {
    onItemClickListener = onItemClickListener_;
  }

  //ClassCardViewHolder_t implementation
  public static class ClassCardViewHolder_t extends RecyclerView.ViewHolder
  {
    ImageView classRoomPic;
    TextView classNumber;
    TextView classTeacherName;

    public ClassCardViewHolder_t(@NonNull View itemView, final OnItemClickListener onItemClickListener_, final List<Class_t> classes)
    {
      super(itemView);

      classRoomPic = (ImageView) itemView.findViewById(R.id.classCardViewClassRoomPic);
      classNumber = (TextView) itemView.findViewById(R.id.classCardViewClassNumber);
      classTeacherName = (TextView) itemView.findViewById(R.id.classCardViewClassTeacher);

      itemView.setOnClickListener(new View.OnClickListener()
      {
        @Override
        public void onClick(View v)
        {
          if(onItemClickListener_ == null)
          {
            return;
          }

          int position = getAdapterPosition();
          if(position == RecyclerView.NO_POSITION)
          {
            return;
          }

          Class_t class_ = classes.get(position);
          onItemClickListener_.OnItemClick(class_);
        }
      });
    }
  }

  @NonNull
  @Override
  public ClassCardViewHolder_t onCreateViewHolder(@NonNull ViewGroup viewGroup, int i)
  {
    LayoutInflater layoutInflater = LayoutInflater.from(context);
    View view = layoutInflater.inflate(R.layout.class_card_view, viewGroup, false);

    ClassCardViewHolder_t classCardViewHolder = new ClassCardViewHolder_t(view, onItemClickListener, classes);
    return classCardViewHolder;
  }

  @Override
  public void onBindViewHolder(@NonNull ClassCardViewHolder_t classCardViewHolder, int position)
  {
    Class_t class_ = classes.get(position);

    //TODO - Setting the image!
    //classCardViewHolder.classRoomPic.setImageIcon(class_.getPicture());
    classCardViewHolder.classNumber.setText("" + class_.getNumber());
    classCardViewHolder.classTeacherName.setText(class_.getClassTeacher());
  }

  @Override
  public int getItemCount() { return classes.size(); }
}
