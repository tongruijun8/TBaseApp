package com.trjx.tbaseapp.test;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.flexbox.FlexboxLayout;
import com.trjx.tbaseapp.R;


/***
 * FlexboxLayout 属性
 *
 * 1.flexDirection 主轴项目排列方向。类似 LinearLayout 的 vertical 和 horizontal，但是 FlexboxLayout 更加强大，还可以设置不同的排列的起点。
 *
 *
 * row：默认值，主轴为水平方向，起点在左端
 * row_reverse：主轴为水平方向，起点在右端
 * column：主轴为垂直方向，起点在上沿
 * column_reverse：主轴为垂直方向，起点在下沿
 *
 *
 * 2.flexWrap 换行方式
 *
 * nowrap ：默认值，不换行
 * wrap：按正常方向换行
 * wrap_reverse：按反方向换行
 *
 * 3.justifyContent 在主轴上的对齐方式。
 *
 *
 * flex_start：默认值，左对齐
 * flex_end：右对齐
 * center： 居中
 * space_between：两端对齐，项目之间的间隔都相等
 * space_around：每个项目两侧的间隔相等。项目之间的间隔比项目与边框的间隔大一倍。
 *
 * 4.alignItems 在副轴轴上如何对齐
 *
 * flex-start：交叉轴的起点对齐
 * flex-end：交叉轴的终点对齐
 * center：交叉轴的中点对齐
 * baseline: 项目的第一行文字的基线对齐，如果没有文本基线，那么默认基线就是左上角
 * stretch：默认值，如果项目未设置高度或设为 auto，将占满整个容器的高度
 *
 *
 *
 * 5.alignContent 多根轴线的对齐方式。子元素有多行时起作用，如果子元素只有一行，该属性不起作用。
 *
 * flex_start：与交叉轴的起点对齐
 * flex_end：与交叉轴的终点对齐
 * center：与交叉轴的中点对齐
 * space_between：与交叉轴两端对齐，轴线之间的间隔平均分布
 * space_around：每根轴线两侧的间隔都相等。所以，轴线之间的间隔比轴线与边框的间隔大一倍
 * stretch（默认值）：轴线占满整个交叉轴。
 *
 * alignContent 和 justifyContent 里面的属性值都是一样的，一个是设置主轴的对齐方式，一个是设置多个轴的对齐方式。
 * 比如是项目是水平换行，alignContent 就是设置垂直方向的对齐方式，justifyContent 就是设置水平方向的对齐方式。而 alignItems 是说第二行的第一个和第一行的第一个怎么对齐。
 *
 * 6.showDividerHorizontal/showDividerVertical 控制显示水平/垂直方向的分割线，值是下面四个取值中的一个或者多个
 *
 * none
 * beginning
 * middle
 * end
 *
 * 7.dividerDrawableHorizontal/dividerDrawableVertical 设置轴线之间水平/垂直方向的分割线。
 *
 * 8.showDivider
 *
 * 控制显示水平和垂直方向的分割线
 *
 * 9.dividerDrawable
 * 设置水平和垂直方向的分割线，如果同时和其他属性使用，比如子元素设置了 justifyContent="space_around"、alignContent="space_between" 等等，可能会看到意料不到的空间，因此应该避免和这些值同时使用。
 *
 *
 * 子元素属性:
 *
 * 1.layout_order
 *
 * 默认情况下子元素的排列方式按照文档流的顺序依次排序，而 order 属性可以控制排列的顺序，负值在前，正值在后，按照从小到大的顺序依次排列。简而言之就是可以定义子元素的排列顺序。
 *
 *
 * 2.layout_flexGrow
 * 定义项目的放大比例，默认为 0，即如果存在剩余空间，也不放大。其实就是 LinearLayout 中的 weight 属性，如果所有项目的 layout_flexGrow 属性都为 1，则它们将等分剩余空间。如果一个项目的 layout_flexGrow 属性为 2，其他项目都为 1，则前者占据的剩余空间将比其他项多一倍。
 *
 * 3.layout_flexShrink
 * 定义项目的缩小比例，默认为 1，即如果空间不足，该项目将缩小。如果所有项目的  layout_flexShrink 属性都为 1，当空间不足时，都将等比例缩小。如果一个项目的 flex-shrink 属性为 0，其他项目都为 1，则空间不足时，前者不缩小。负值对该属性无效。
 *
 * 4.layout_alignSelf
 * 允许单个子元素有与其他子元素不一样的对齐方式，可覆盖 alignItems 属性。默认值为 auto，表示继承父元素的 alignItems 属性，如果没有父元素，则等同于 stretch。
 * 该属性可能取 6 个值，除了 auto，其他都与 align_items 属性完全一致：
 *
 * auto (default)
 * flex_start
 * flex_end
 * center
 * baseline
 * stretch
 *
 * 5.layout_flexBasisPercent
 * 定义了在分配多余空间之前，子元素占据的主轴空间的百分比。它的默认值为 auto，即子元素的本来大小。
 * 如果设置了这个值，那么通过这个属性计算的值将会覆盖 layout_width 或者 layout_height 的值。但是需要注意，这个值只有设置了父容器的长度时才有效（也就是 MeasureSpec mode 是 MeasureSpec.EXACTLY）。
 *
 * 6.layout_minWidth/layout_minHeight
 *
 * 强制限制 FlexboxLayout 的子元素（宽或高）不会小于最小值，不管 layout_flexShrink 这个属性的值为多少，子元素不会被缩小到小于设置的这个最小值。
 *
 *
 * 7.layout_maxWidth/layout_maxHeight
 *
 * 强制限制 FlexboxLayout 子元素不会大于这个最大值, 不管 layout_flexGrow 的值为多少，子元素不会被放大到超过这个最大值。
 *
 * 8.layout_wrapBefore
 *
 * 控制强制换行，默认值为 false，如果将一个子元素的这个属性设置为 true，那么这个子元素将会成为一行的第一个元素。这个属性将忽略 flex_wrap 设置的 noWrap 值。
 *
 *
 *

 */
public class TestFlexboxLayoutActivity extends AppCompatActivity {

    private FlexboxLayout flexboxLayout;

    private LayoutInflater inflater;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_flexbox_layout);

        flexboxLayout = findViewById(R.id.flexbox);
        inflater = LayoutInflater.from(this);
        initData();

    }

    private void initData() {
//        List<String> list = new ArrayList<>();
        String testStr = "测试数据";
        for (int i = 0; i < 20; i++) {
            int num = (int) (Math.random() * 5);
            StringBuilder str = new StringBuilder(testStr);
            for (int j = 0; j < num; j++) {
                str.append(testStr);
            }

            View view = inflater.inflate(R.layout.item_flexbox, null);
            TextView textView = view.findViewById(R.id.item_text1);
            textView.setText(str.toString());
            flexboxLayout.addView(view);

        }



    }
}
