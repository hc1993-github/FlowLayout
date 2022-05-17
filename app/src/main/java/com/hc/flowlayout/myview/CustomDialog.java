package com.hc.flowlayout.myview;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class CustomDialog extends Dialog {
    private Builder builder;
    private Button leftBtn;
    private Button rightBtn;

    private CustomDialog(Builder builder) {
        super(builder.context);
        this.builder = builder;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_HIDE_NAVIGATION);
        this.getWindow().getDecorView().setOnSystemUiVisibilityChangeListener(new View.OnSystemUiVisibilityChangeListener() {
            @Override
            public void onSystemUiVisibilityChange(int visibility) {
                int uiOptions = View.SYSTEM_UI_FLAG_LAYOUT_STABLE |
                        View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION |
                        View.SYSTEM_UI_FLAG_FULLSCREEN |
                        View.SYSTEM_UI_FLAG_HIDE_NAVIGATION |
                        View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN;
                uiOptions |= 0x00001000;
                CustomDialog.this.getWindow().getDecorView().setSystemUiVisibility(uiOptions);
            }
        });
        setContentView(builder.layout);
        setCancelable(false);
        initViews();
    }

    private void initViews() {
        leftBtn = findViewById(builder.leftBtnId);
        if (leftBtn != null) {
            leftBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    builder.listener.onLeftClick(CustomDialog.this);
                }
            });
        }

        rightBtn = findViewById(builder.rightBtnId);
        if (rightBtn != null) {
            rightBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    builder.listener.onRightClick(CustomDialog.this);
                }
            });
        }
        if (builder.rightFiveId != -1) {
            TextView tv = findViewById(builder.rightFiveId);
            tv.setText(builder.rightFiveDegree);
        }
        if (builder.rightSmallId != -1) {
            TextView tv = findViewById(builder.rightSmallId);
            tv.setText(builder.rightSmallDegree);
        }
        if (builder.leftFiveId != -1) {
            TextView tv = findViewById(builder.leftFiveId);
            tv.setText(builder.leftFiveDegree);
        }
        if (builder.leftSmallId != -1) {
            TextView tv = findViewById(builder.leftSmallId);
            tv.setText(builder.leftSmallDegree);
        }

        if (builder.rightGlassFiveId != -1) {
            TextView tv = findViewById(builder.rightGlassFiveId);
            tv.setText(builder.rightGlassFiveDegree);
        }
        if (builder.rightGlassSmallId != -1) {
            TextView tv = findViewById(builder.rightGlassSmallId);
            tv.setText(builder.rightGlassSmallDegree);
        }
        if (builder.leftGlassFiveId != -1) {
            TextView tv = findViewById(builder.leftGlassFiveId);
            tv.setText(builder.leftGlassFiveDegree);
        }
        if (builder.leftGlassSmallId != -1) {
            TextView tv = findViewById(builder.leftGlassSmallId);
            tv.setText(builder.leftGlassSmallDegree);
        }
    }

    public static class Builder {
        private Context context;
        private String title;
        private int leftBtnId;
        private int rightBtnId;
        private int layout;
        private OnClickListener listener;
        private String rightFiveDegree;
        private String rightSmallDegree;
        private String leftFiveDegree;
        private String leftSmallDegree;
        private String rightGlassFiveDegree;
        private String rightGlassSmallDegree;
        private String leftGlassFiveDegree;
        private String leftGlassSmallDegree;
        private int rightFiveId = -1;
        private int rightSmallId = -1;
        private int leftFiveId = -1;
        private int leftSmallId = -1;
        private int rightGlassFiveId = -1;
        private int rightGlassSmallId = -1;
        private int leftGlassFiveId = -1;
        private int leftGlassSmallId = -1;

        public Builder setContext(Context context) {
            this.context = context;
            return this;
        }

        public Builder setTitle(String title) {
            this.title = title;
            return this;
        }

        public Builder setLeftBtnId(int leftBtnId) {
            this.leftBtnId = leftBtnId;
            return this;
        }

        public Builder setRightBtnId(int rightBtnId) {
            this.rightBtnId = rightBtnId;
            return this;
        }

        public Builder setLayout(int layout) {
            this.layout = layout;
            return this;
        }

        public Builder setListener(OnClickListener listener) {
            this.listener = listener;
            return this;
        }

        public Builder setRightFiveDegree(int id, String text) {
            this.rightFiveId = id;
            this.rightFiveDegree = text;
            return this;
        }

        public Builder setRightSmallDegree(int id, String text) {
            this.rightSmallId = id;
            this.rightSmallDegree = text;
            return this;
        }

        public Builder setLeftFiveDegree(int id, String text) {
            this.leftFiveId = id;
            this.leftFiveDegree = text;
            return this;
        }

        public Builder setLeftSmallDegree(int id, String text) {
            this.leftSmallId = id;
            this.leftSmallDegree = text;
            return this;
        }

        public Builder setRightGlassFiveDegree(int id, String text) {
            this.rightGlassFiveId = id;
            this.rightGlassFiveDegree = text;
            return this;
        }

        public Builder setRightGlassSmallDegree(int id, String text) {
            this.rightGlassSmallId = id;
            this.rightGlassSmallDegree = text;
            return this;
        }

        public Builder setLeftGlassFiveDegree(int id, String text) {
            this.leftGlassFiveId = id;
            this.leftGlassFiveDegree = text;
            return this;
        }

        public Builder setLeftGlassSmallDegree(int id, String text) {
            this.leftGlassSmallId = id;
            this.leftGlassSmallDegree = text;
            return this;
        }

        public CustomDialog build() {
            return new CustomDialog(this);
        }
    }

    public interface OnClickListener {
        void onLeftClick(Dialog dialog);

        void onRightClick(Dialog dialog);
    }
}
