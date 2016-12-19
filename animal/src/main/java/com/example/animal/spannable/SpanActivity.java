package com.example.animal.spannable;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.text.method.LinkMovementMethod;
import android.text.style.BackgroundColorSpan;
import android.text.style.ClickableSpan;
import android.text.style.ForegroundColorSpan;
import android.text.style.ImageSpan;
import android.text.style.UnderlineSpan;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.animal.R;

public class SpanActivity extends AppCompatActivity {
    private TextView mTextView1, mTextView2;
    private MapTextView mMapText;

    /**
     * 对应的参数：

     start： 指定Span的开始位置

     end： 指定Span的结束位置，并不包括这个位置。

     flags：取值有如下四个：

     Spannable.SPAN_EXCLUSIVE_INCLUSIVE：在 Span前面输入的字符不应用 Span的效果，在后面输入的字符应用Span效果。

     Spannable.SPAN_INCLUSIVE_EXCLUSIVE：在 Span前面输入的字符应用 Span 的效果，在后面输入的字符不应用Span效果。

     Spannable.SPAN_INCUJSIVE_INCLUSIVE：在 Span前后输入的字符都应用 Span 的效果。

     Spannable.SPAN_EXCLUSIVE_EXCLUSIVE：前后都不包括。

     what： 对应的各种Span，不同的Span对应不同的样式。已知的可用类有：

     BackgroundColorSpan : 文本背景色

     ForegroundColorSpan : 文本颜色

     MaskFilterSpan : 修饰效果，如模糊(BlurMaskFilter)浮雕

     RasterizerSpan : 光栅效果

     StrikethroughSpan : 删除线

     SuggestionSpan : 相当于占位符

     UnderlineSpan : 下划线

     AbsoluteSizeSpan : 文本字体（绝对大小）

     DynamicDrawableSpan : 设置图片，基于文本基线或底部对齐。

     ImageSpan : 图片

     RelativeSizeSpan : 相对大小（文本字体）

     ScaleXSpan : 基于x轴缩放

     StyleSpan : 字体样式：粗体、斜体等

     SubscriptSpan : 下标（数学公式会用到）

     SuperscriptSpan : 上标（数学公式会用到）

     TextAppearanceSpan : 文本外貌（包括字体、大小、样式和颜色）

     TypefaceSpan : 文本字体

     URLSpan : 文本超链接

     ClickableSpan : 点击事件
     */

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_span);
        mTextView1 = (TextView) findViewById(R.id.span1);
        mTextView2 = (TextView) findViewById(R.id.span2);
        mMapText = (MapTextView) findViewById(R.id.map_text);
        mMapText.setKeyText("姓名:");
        mMapText.setValueText("姓名");
        mMapText.setKeyTextColor(Color.BLACK);
        mMapText.setValueTextColor(Color.BLUE);
        mMapText.invalidateMapText();


        mTextView1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                model1();

            }
        });

        mTextView2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                model2();
            }
        });
    }

    private void model2() {
        SpannableStringBuilder spannBuilder = new SpannableStringBuilder();
        spannBuilder.append("哈哈哈哈啊啊啊啊呵呵呵呵");
        //字体颜色
        ForegroundColorSpan groundColor = new ForegroundColorSpan(Color.RED);
        spannBuilder.setSpan(groundColor, 0, 4, Spanned.SPAN_EXCLUSIVE_INCLUSIVE);
        //背景色
        BackgroundColorSpan backgroundColor = new BackgroundColorSpan(Color.BLUE);
        spannBuilder.setSpan(backgroundColor, 4, 8, Spanned.SPAN_EXCLUSIVE_INCLUSIVE);
        //图片
        ImageSpan imageSpan = new ImageSpan(this, R.mipmap.ic_launcher);
        spannBuilder.setSpan(imageSpan, 8, 10, Spanned.SPAN_EXCLUSIVE_INCLUSIVE);
        //监听事件
        ClickableSpan clickSpan = new ClickableSpan() {
            @Override
            public void onClick(View view) {
                Toast.makeText(SpanActivity.this, "不要点我", Toast.LENGTH_SHORT).show();
            }
        };
        spannBuilder.setSpan(clickSpan, 8, 10, Spanned.SPAN_EXCLUSIVE_INCLUSIVE);
        //下划线
        UnderlineSpan lineSpan = new UnderlineSpan();
        spannBuilder.setSpan(lineSpan, 0, 4, Spanned.SPAN_EXCLUSIVE_INCLUSIVE);
        mTextView2.setText(spannBuilder);
        //设置事件监听必须加这一句代码，否则无效
        mTextView2.setMovementMethod(LinkMovementMethod.getInstance());

        /*
          还有删除线 StrikethroughSpan
          AbsoluteSizeSpan 设置字体大小
           粗体 StyleSpan=new  StyleSpan(Typeface.BOLD);
           斜体 StyleSpan=new  StyleSpan(Typeface.ITALIC);
           粗斜体 StyleSpan=new  StyleSpan(Typeface.BOLD_ITALIC);
         */


    }

    private void model1() {
        SpannableString span1 = new SpannableString("哈哈哈哈哈哈啊啊啊啊啊啊啊");
        ForegroundColorSpan groundColor = new ForegroundColorSpan(Color.parseColor("#3F51B5"));
        span1.setSpan(groundColor, 0, 6, Spannable.SPAN_EXCLUSIVE_INCLUSIVE);
        mTextView1.setText(span1);
    }
}
