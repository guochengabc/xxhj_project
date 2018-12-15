package com.kongtiaoapp.xxhj.ui.view;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.widget.TextView;

import com.kongtiaoapp.xxhj.App;
import com.kongtiaoapp.xxhj.R;
import com.kongtiaoapp.xxhj.utils.emoji.ToastUtils;


public class DialogPrompt extends Dialog {

    public DialogPrompt(Context context) {
        super(context);
    }

    public DialogPrompt(Context context, int theme) {
        super(context, theme);
    }

    public static class Builder {
        private Context context;
        private String title;
        private String positiveButtonText;
        private String negativeButtonText;
        private View contentView;
        private OnClickListener positiveButtonClickListener;
        private OnClickListener negativeButtonClickListener;
        private String connectType = "A";//A代表正常环境  B代表测试环境  C代表本地环境

        public Builder(Context context) {
            this.context = context;

        }

        /**
         * Set the Dialog message from resource
         *
         * @param
         * @return
         */
//		public Builder setMessage(int message) {
//			this.message = (String) context.getText(message);
//			return this;
//		}

        /**
         * Set the Dialog title from resource
         *
         * @param title
         * @return
         */
        public Builder setTitle(int title) {
            this.title = (String) context.getText(title);
            return this;
        }

        /**
         * Set the Dialog title from String
         *
         * @param title
         * @return
         */

        public Builder setTitle(String title) {
            this.title = title;
            return this;
        }

        public Builder setContentView(View v) {
            this.contentView = v;
            return this;
        }

        /**
         * Set the positive button resource and it's listener
         *
         * @param positiveButtonText
         * @return
         */
        public Builder setPositiveButton(int positiveButtonText,
                                         OnClickListener listener) {
            this.positiveButtonText = (String) context
                    .getText(positiveButtonText);
            this.positiveButtonClickListener = listener;
            return this;
        }

        public Builder setPositiveButton(String positiveButtonText,
                                         OnClickListener listener) {
            this.positiveButtonText = positiveButtonText;
            this.positiveButtonClickListener = listener;
            return this;
        }

        public Builder setNegativeButton(int negativeButtonText,
                                         OnClickListener listener) {
            this.negativeButtonText = (String) context
                    .getText(negativeButtonText);
            this.negativeButtonClickListener = listener;
            return this;
        }

        public Builder setNegativeButton(String negativeButtonText,
                                         OnClickListener listener) {
            this.negativeButtonText = negativeButtonText;
            this.negativeButtonClickListener = listener;
            return this;
        }


        public DialogPrompt create() {
            LayoutInflater inflater = (LayoutInflater) context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            // instantiate the dialog with the custom Theme
            final DialogPrompt dialog = new DialogPrompt(context, R.style.Dialog);
            View layout = inflater.inflate(R.layout.diag_prompt, null);
            dialog.addContentView(layout, new LayoutParams(
                    LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT));
            TextView txt_title = ((TextView) layout.findViewById(R.id.txt_title));
            txt_title.setText(title);
            if (positiveButtonText != null) {
                ((TextView) layout.findViewById(R.id.txt_sure))
                        .setText(positiveButtonText);
                if (positiveButtonClickListener != null) {
                    ((TextView) layout.findViewById(R.id.txt_sure))
                            .setOnClickListener(new View.OnClickListener() {
                                public void onClick(View v) {
                                    positiveButtonClickListener.onClick(dialog,
                                            DialogInterface.BUTTON_POSITIVE);

                                }
                            });
                }
            } else {
                // if no confirm button just set the visibility to GONE
                layout.findViewById(R.id.txt_sure).setVisibility(
                        View.GONE);
            }
            // set the cancel button
            if (negativeButtonText != null) {
                ((TextView) layout.findViewById(R.id.txt_cancel))
                        .setText(negativeButtonText);
                if (negativeButtonClickListener != null) {
                    ((TextView) layout.findViewById(R.id.txt_cancel))
                            .setOnClickListener(new View.OnClickListener() {
                                public void onClick(View v) {
                                    negativeButtonClickListener.onClick(dialog,
                                            DialogInterface.BUTTON_NEGATIVE);
                                }
                            });
                }
            } else {
                // if no confirm button just set the visibility to GONE
                layout.findViewById(R.id.txt_cancel).setVisibility(
                        View.GONE);
            }


            dialog.setContentView(layout);
            dialog.setCanceledOnTouchOutside(false);
            return dialog;
        }

        public DialogPrompt createEnvironment() {
            LayoutInflater inflater = (LayoutInflater) context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            // instantiate the dialog with the custom Theme
            final DialogPrompt dialog = new DialogPrompt(context, R.style.Dialog);
            View layout = inflater.inflate(R.layout.environment_ttest, null);
            dialog.addContentView(layout, new LayoutParams(
                    LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT));
            TextView txt_title = ((TextView) layout.findViewById(R.id.txt_title));
            TextView txt_normal = ((TextView) layout.findViewById(R.id.txt_normal));
            TextView txt_test = ((TextView) layout.findViewById(R.id.txt_test));
            TextView txt_native = ((TextView) layout.findViewById(R.id.txt_native));
            /**正常环境*/
            txt_normal.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    connectType = "A";
                    txt_normal.setTextColor(context.getResources().getColor(R.color.modify_select));
                    txt_test.setTextColor(context.getResources().getColor(R.color.modify_unselect));
                    txt_native.setTextColor(context.getResources().getColor(R.color.modify_unselect));
                }
            });
            /**测试环境*/
            txt_test.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    connectType = "B";
                    txt_normal.setTextColor(context.getResources().getColor(R.color.modify_unselect));
                    txt_test.setTextColor(context.getResources().getColor(R.color.modify_select));
                    txt_native.setTextColor(context.getResources().getColor(R.color.modify_unselect));
                }
            });
            /**本地环境*/
            txt_native.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    connectType = "C";
                    txt_normal.setTextColor(context.getResources().getColor(R.color.modify_unselect));
                    txt_test.setTextColor(context.getResources().getColor(R.color.modify_unselect));
                    txt_native.setTextColor(context.getResources().getColor(R.color.modify_select));
                }
            });
            String environment = App.sp.getEnvironment();
            switch (App.sp.getEnvironment()) {
                case "https://api.xiaoxitech.cn/v3/Process":
                    txt_normal.setTextColor(context.getResources().getColor(R.color.modify_select));
                    txt_test.setTextColor(context.getResources().getColor(R.color.modify_unselect));
                    txt_native.setTextColor(context.getResources().getColor(R.color.modify_unselect));
                    break;
                case "https://api.xiaoxitech.cn/v3_test/Process":
                    txt_normal.setTextColor(context.getResources().getColor(R.color.modify_unselect));
                    txt_test.setTextColor(context.getResources().getColor(R.color.modify_select));
                    txt_native.setTextColor(context.getResources().getColor(R.color.modify_unselect));
                    break;
                case "http://10.0.2.2:8080/ktjnAPP/Process":
                    txt_normal.setTextColor(context.getResources().getColor(R.color.modify_unselect));
                    txt_test.setTextColor(context.getResources().getColor(R.color.modify_unselect));
                    txt_native.setTextColor(context.getResources().getColor(R.color.modify_select));
                    break;
            }
            txt_title.setText(title);
            if (positiveButtonText != null) {
                ((TextView) layout.findViewById(R.id.txt_sure))
                        .setText(positiveButtonText);
                if (positiveButtonClickListener != null) {
                    ((TextView) layout.findViewById(R.id.txt_sure))
                            .setOnClickListener(new View.OnClickListener() {
                                public void onClick(View v) {
                                    positiveButtonClickListener.onClick(dialog,
                                            DialogInterface.BUTTON_POSITIVE);
                                    switch (connectType) {
                                        case "A":
                                            App.sp.setEnvironment("https://api.xiaoxitech.cn/v3/Process");
                                            App.sp.setEnvironmentImageDocument("https://xiaoxitech.cn/");
                                            ToastUtils.showToast(context, "正式环境切换成功");
                                            txt_normal.setTextColor(context.getResources().getColor(R.color.modify_select));
                                            txt_test.setTextColor(context.getResources().getColor(R.color.modify_unselect));
                                            txt_native.setTextColor(context.getResources().getColor(R.color.modify_unselect));
                                            break;
                                        case "B":
                                            App.sp.setEnvironment("https://api.xiaoxitech.cn/v3_test/Process");
                                            App.sp.setEnvironmentImageDocument("https://xiaoxitech.cn/");
                                            ToastUtils.showToast(context, "测试环境切换成功");
                                            txt_normal.setTextColor(context.getResources().getColor(R.color.modify_unselect));
                                            txt_test.setTextColor(context.getResources().getColor(R.color.modify_select));
                                            txt_native.setTextColor(context.getResources().getColor(R.color.modify_unselect));
                                            break;
                                        case "C":
                                            App.sp.setEnvironment("http://10.0.2.2:8080/ktjnAPP/Process");
                                            App.sp.setEnvironmentImageDocument("https://192.168.1.141/");
                                            ToastUtils.showToast(context, "本地环境切换成功");
                                            txt_normal.setTextColor(context.getResources().getColor(R.color.modify_unselect));
                                            txt_test.setTextColor(context.getResources().getColor(R.color.modify_unselect));
                                            txt_native.setTextColor(context.getResources().getColor(R.color.modify_select));
                                            break;
                                        default:
                                            App.sp.setEnvironment("https://api.xiaoxitech.cn/v3/Process");
                                            App.sp.setEnvironmentImageDocument("https://xiaoxitech.cn/");
                                            ToastUtils.showToast(context, "正式环境切换成功");
                                            txt_normal.setTextColor(context.getResources().getColor(R.color.modify_select));
                                            txt_test.setTextColor(context.getResources().getColor(R.color.modify_unselect));
                                            txt_native.setTextColor(context.getResources().getColor(R.color.modify_unselect));
                                            break;
                                    }
                                }
                            });
                }
            } else {
                // if no confirm button just set the visibility to GONE
                layout.findViewById(R.id.txt_sure).setVisibility(
                        View.GONE);
            }
            // set the cancel button
            if (negativeButtonText != null) {
                ((TextView) layout.findViewById(R.id.txt_cancel))
                        .setText(negativeButtonText);
                if (negativeButtonClickListener != null) {
                    ((TextView) layout.findViewById(R.id.txt_cancel))
                            .setOnClickListener(new View.OnClickListener() {
                                public void onClick(View v) {
                                    negativeButtonClickListener.onClick(dialog,
                                            DialogInterface.BUTTON_NEGATIVE);
                                }
                            });
                }
            } else {
                // if no confirm button just set the visibility to GONE
                layout.findViewById(R.id.txt_cancel).setVisibility(
                        View.GONE);
            }


            dialog.setContentView(layout);
            dialog.setCanceledOnTouchOutside(false);
            return dialog;
        }


    }


}
