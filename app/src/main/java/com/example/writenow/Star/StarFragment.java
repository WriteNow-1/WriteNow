package com.example.writenow.Star;
import com.example.writenow.Write.ResultActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.example.writenow.R;
public class StarFragment extends Fragment {

    private LinearLayout buttonContainer;
    private DatabaseReference databaseReference;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_star, container, false);
        buttonContainer = view.findViewById(R.id.buttonContainer);
        databaseReference = FirebaseDatabase.getInstance().getReference("결과");

        createUniversityButtons();

        return view;
    }

    private void createUniversityButtons() {
        databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot universitySnapshot : snapshot.getChildren()) {
                    String university = universitySnapshot.getKey();
                    Button button = createButton(university);
                    buttonContainer.addView(button);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                // Handle database error
            }
        });
    }

    private Button createButton(final String university) {
        Button button = new Button(requireContext());
        button.setLayoutParams(new LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT
        ));
        button.setText(university);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showSubjectButtons(university);
            }
        });
        return button;
    }



    private void showSubjectButtons(final String university) {
        buttonContainer.setVisibility(View.GONE);
        LinearLayout subjectLayout = requireView().findViewById(R.id.subjectLayout);
        subjectLayout.setVisibility(View.VISIBLE);

        Button subjectButton1 = requireView().findViewById(R.id.subjectButton1);
        Button subjectButton2 = requireView().findViewById(R.id.subjectButton2);
        Button freetextButton = requireView().findViewById(R.id.freetextButton);

        subjectButton1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loadResult(university, "1");
            }
        });

        subjectButton2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loadResult(university, "2");
            }
        });

        freetextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loadResult(university, "자율문항");
            }
        });
    }

    private void loadResult(String university, String questionNumber) {
        DatabaseReference universityRef = databaseReference.child(university).child(questionNumber).child("결과");
        universityRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists()) {
                    String result = snapshot.getValue(String.class);
                    Intent intent = new Intent(getActivity(), ResultActivity.class);
                    intent.putExtra("result", result);
                    startActivity(intent);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                // Handle database error
            }
        });
    }
}
