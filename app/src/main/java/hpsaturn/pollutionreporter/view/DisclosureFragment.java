package hpsaturn.pollutionreporter.view;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import hpsaturn.pollutionreporter.MainActivity;
import hpsaturn.pollutionreporter.R;

/**
 * Created by Antonio Vanegas @hpsaturn on 5/22/21.
 */
public class DisclosureFragment extends DialogFragment {

    public static final String TAG = DisclosureFragment.class.getSimpleName();

    private static final String key_dialog_desc = "key_desc";
    private static final String key_dialog_img = "key_img";

    private int resource_desc;
    private int resource_img;

    public static DisclosureFragment newInstance(int desc, int img){

        DisclosureFragment dialog = new DisclosureFragment();

        Bundle args = new Bundle();
        args.putInt(key_dialog_desc, desc);
        args.putInt(key_dialog_img, img);
        dialog.setArguments(args);

        return dialog;

    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        resource_desc =getArguments().getInt(key_dialog_desc);
        resource_img =getArguments().getInt(key_dialog_img);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.disclosure_dialog_input, container, false);

        TextView mDisclosureText = (TextView) v.findViewById(R.id.tv_disclosure_text);
        mDisclosureText.setText(getString(resource_desc));

        ImageView mDisclosureImg = (ImageView) v.findViewById(R.id.img_disclosure);
        mDisclosureImg.setImageDrawable(getResources().getDrawable(resource_img));

        Button mButtonContinue = (Button) v.findViewById(R.id.bt_alias_continue);
        mButtonContinue.setOnClickListener(onClickContinueListener);

        Button mButtonCancel = (Button) v.findViewById(R.id.bt_alias_cancel);
        mButtonCancel.setOnClickListener(onClickCancelListener);


        return v;

    }

    private final View.OnClickListener onClickContinueListener = view -> {
        if(resource_desc==R.string.msg_disclosure_gps){
            getMain().startPermissionsGPSFlow();
        }
        else if (resource_desc==R.string.msg_disclosure_ble){
            getMain().startPermissionsBLEFlow();
        }
        getDialog().dismiss();
    };

    private View.OnClickListener onClickCancelListener = view -> {
        getDialog().dismiss();
    };

    private MainActivity getMain() {
        return ((MainActivity) getActivity());
    }

}
