package com.pro.salon.cattocdi.fragments;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.RatingBar;
import android.widget.TextView;

import com.pro.salon.cattocdi.R;
import com.pro.salon.cattocdi.adapter.CommentAdapter;
import com.pro.salon.cattocdi.models.Comment;
import com.pro.salon.cattocdi.models.Salon;
import com.pro.salon.cattocdi.service.ApiClient;
import com.pro.salon.cattocdi.service.SalonClient;
import com.pro.salon.cattocdi.utils.AlertError;
import com.pro.salon.cattocdi.utils.MyContants;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the

 * to handle interaction events.
 */
@SuppressLint("ValidFragment")
public class ReviewsFragment extends Fragment {


    private Salon salon;
    private RatingBar tvRatingNumber;
    private TextView tvTotalReviews;
    private RecyclerView rvComments;
    private List<Comment> comments;


    public ReviewsFragment() {
        // Required empty public constructor
    }

    @SuppressLint("ValidFragment")
    public ReviewsFragment(Salon salon) {
        this.salon = salon;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_reviews, container, false);

        tvRatingNumber = view.findViewById(R.id.fg_reviews_sum);
        //tvRatingNumber.setRating(salon.getRatingNumber());

        tvTotalReviews = view.findViewById(R.id.salon_detail_total_reviews_tv);
       // tvTotalReviews.setText(salon.getReviewsAmount() + " Đánh giá");

        rvComments = view.findViewById(R.id.salon_detail_comment_rv);
        ViewCompat.setNestedScrollingEnabled(rvComments, false);
        rvComments.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false));
        comments = new ArrayList<>();
        rvComments.setAdapter(new CommentAdapter(comments));
        ApiClient.getInstance()
                .create(SalonClient.class)
                .getReview("Bearer " + MyContants.TOKEN)
                .enqueue(new Callback<List<Comment>>() {
                    @Override
                    public void onResponse(Call<List<Comment>> call, Response<List<Comment>> response) {
                        if(response.code() == 200){
                            tvRatingNumber.setRating(comments.get(0).getRating());
                            tvTotalReviews.setText(comments.size() + 1 + " Đánh giá");
                            rvComments.setAdapter(new CommentAdapter(comments));
                        }else{
                            AlertError.showDialogLoginFail(getContext(), "Có lỗi xảy ra vui lòng xem lại kết nối");
                        }
                    }

                    @Override
                    public void onFailure(Call<List<Comment>> call, Throwable t) {
                        AlertError.showDialogLoginFail(getContext(), "Có lỗi xảy ra vui lòng xem lại kết nối");
                    }
                });

        setProgressBar(view);

        return view;
    }

    private void setProgressBar(View view){

        ProgressBar pb1, pb2, pb3, pb4, pb5;
        TextView tv1, tv2, tv3, tv4, tv5;
        pb1 = view.findViewById(R.id.salon_detail_review_1_pb);
        tv1 = view.findViewById(R.id.salon_detail_review_1_tv);
        pb2 = view.findViewById(R.id.salon_detail_review_2_pb);
        tv2 = view.findViewById(R.id.salon_detail_review_2_tv);
        pb3 = view.findViewById(R.id.salon_detail_review_3_pb);
        tv3 = view.findViewById(R.id.salon_detail_review_3_tv);
        pb4 = view.findViewById(R.id.salon_detail_review_4_pb);
        tv4 = view.findViewById(R.id.salon_detail_review_4_tv);
        pb5 = view.findViewById(R.id.salon_detail_review_5_pb);
        tv5 = view.findViewById(R.id.salon_detail_review_5_tv);

        int count1 = 0, count2 = 0, count3 = 0, count4 = 0, count5 = 0;
        for (Comment com :
               comments) {
            switch (com.getRating()){
                case 1:
                    count1++;
                    break;
                case 2:
                    count2++;
                    break;
                case 3:
                    count3++;
                    break;
                case 4:
                    count4++;
                    break;
                case 5:
                    count5++;
                    break;
            }
        }

        pb1.setProgress(Math.round((float) count1 / salon.getReviewsAmount()));
        pb2.setProgress(Math.round((float) count2 / salon.getReviewsAmount()));
        pb3.setProgress(Math.round((float) count3 / salon.getReviewsAmount()));
        pb4.setProgress(Math.round((float) count4 / salon.getReviewsAmount()));
        pb5.setProgress(Math.round((float) count5 / salon.getReviewsAmount()));

        tv1.setText(Math.round((float) count1 / salon.getReviewsAmount()) + "");
        tv2.setText(Math.round((float) count2 / salon.getReviewsAmount()) + "");
        tv3.setText(Math.round((float) count3 / salon.getReviewsAmount()) + "");
        tv4.setText(Math.round((float) count4 / salon.getReviewsAmount()) + "");
        tv5.setText(Math.round((float) count5 / salon.getReviewsAmount()) + "");


    }


}
