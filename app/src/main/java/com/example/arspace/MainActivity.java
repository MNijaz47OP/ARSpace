package com.example.arspace;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.ar.core.Anchor;
import com.google.ar.core.HitResult;
import com.google.ar.core.Plane;
import com.google.ar.sceneform.AnchorNode;
import com.google.ar.sceneform.rendering.ModelRenderable;
import com.google.ar.sceneform.ux.ArFragment;
import com.google.ar.sceneform.ux.BaseArFragment;
import com.google.ar.sceneform.ux.TransformableNode;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    ArFragment arFragment;
    private ModelRenderable booksRenderable,
                            chairRenderable,
                            drawerRenderable,
                            lampRenderable,
                            sofaRenderable,
                            tableRenderable;
    ImageView books,chair,drawer,lamp,sofa,table;

    View[] arrayView;

    int selected = 1;   //books is Choosen as Default

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //Initialising the fragment
        arFragment = (ArFragment)getSupportFragmentManager().findFragmentById(R.id.ux_fragment);
        setArrayView();
        setClickListner();
        setupModel();

        arFragment.setOnTapArPlaneListener((hitResult, plane, motionEvent) -> {
            //When user taps on plane we will add the model
                Anchor anchor = hitResult.createAnchor();
                AnchorNode anchorNode = new AnchorNode(anchor);
                anchorNode.setParent(arFragment.getArSceneView().getScene());
                createModel(anchorNode,selected);//
        });

    }
    //Setting Up the Models For Rendering
    private void setupModel() {
        ModelRenderable.builder()
                .setSource(this,R.raw.books)
                .build().thenAccept(renderable -> booksRenderable = renderable)
                .exceptionally(
                        throwable -> {
                            Toast.makeText(this,"Unable To Load Model", Toast.LENGTH_SHORT).show();
                        return null;
                }
                );
        ModelRenderable.builder()
                .setSource(this,R.raw.chair)
                .build().thenAccept(renderable -> chairRenderable = renderable)
                .exceptionally(
                        throwable -> {
                            Toast.makeText(this,"Unable To Load Model", Toast.LENGTH_SHORT).show();
                            return null;
                        }
                );
        ModelRenderable.builder()
                .setSource(this,R.raw.drawer)
                .build().thenAccept(renderable -> drawerRenderable = renderable)
                .exceptionally(
                        throwable -> {
                            Toast.makeText(this,"Unable To Load Model", Toast.LENGTH_SHORT).show();
                            return null;
                        }
                );
        ModelRenderable.builder()
                .setSource(this,R.raw.lamp)
                .build().thenAccept(renderable -> lampRenderable = renderable)
                .exceptionally(
                        throwable -> {
                            Toast.makeText(this,"Unable To Load Model", Toast.LENGTH_SHORT).show();
                            return null;
                        }
                );
        ModelRenderable.builder()
                .setSource(this,R.raw.sofa)
                .build().thenAccept(renderable -> sofaRenderable = renderable)
                .exceptionally(
                        throwable -> {
                            Toast.makeText(this,"Unable To Load Model", Toast.LENGTH_SHORT).show();
                            return null;
                        }
                );
        ModelRenderable.builder()
                .setSource(this,R.raw.table)
                .build().thenAccept(renderable -> tableRenderable = renderable)
                .exceptionally(
                        throwable -> {
                            Toast.makeText(this,"Unable To Load Model", Toast.LENGTH_SHORT).show();
                            return null;
                        }
                );

    }
    //Creating the Render based on the selection
    private void createModel(AnchorNode anchorNode, int selected) {
        if(selected == 1){
            TransformableNode books = new TransformableNode(arFragment.getTransformationSystem());
            books.setParent(anchorNode);
            books.setRenderable(booksRenderable);
            books.select();
        }
        else if(selected == 2){
            TransformableNode chair = new TransformableNode(arFragment.getTransformationSystem());
            chair.setParent(anchorNode);
            chair.setRenderable(chairRenderable);
            chair.select();
        }
        else if(selected == 3){
            TransformableNode drawer = new TransformableNode(arFragment.getTransformationSystem());
            drawer.setParent(anchorNode);
            drawer.setRenderable(drawerRenderable);
            drawer.select();
        }
        else if(selected == 4){
            TransformableNode lamp = new TransformableNode(arFragment.getTransformationSystem());
            lamp.setParent(anchorNode);
            lamp.setRenderable(lampRenderable);
            lamp.select();
        }
        else if(selected == 5){
            TransformableNode sofa = new TransformableNode(arFragment.getTransformationSystem());
            sofa.setParent(anchorNode);
            sofa.setRenderable(sofaRenderable);
            sofa.select();
        }
        else if(selected == 6){
            TransformableNode table = new TransformableNode(arFragment.getTransformationSystem());
            table.setParent(anchorNode);
            table.setRenderable(tableRenderable);
            table.select();
        }

    }
    //Creating an ClickListner for the images
    private void setClickListner() {
        for(int i=0;i<arrayView.length;i++){
            arrayView[i].setOnClickListener(this);
        }
    }
    //Adding models to the View Array for Comparison During Selection Of the Model to be rendered
    private void setArrayView()  {
        books = (ImageView)findViewById(R.id.books);
        chair = (ImageView)findViewById(R.id.chair);
        drawer = (ImageView)findViewById(R.id.drawer);
        lamp = (ImageView)findViewById(R.id.lamp);
        sofa = (ImageView)findViewById(R.id.sofa);
        table = (ImageView)findViewById(R.id.table);
        arrayView = new View[]{
                books,chair,drawer,lamp,sofa,table
        };
    }
    //Choosing the model to be Rendered
    @Override
    public void onClick(View view) {
        if(view.getId() == R.id.books)
            selected = 1;
        else if(view.getId() == R.id.chair)
            selected = 2;
        else if(view.getId() == R.id.drawer)
            selected = 3;
        else if (view.getId() == R.id.lamp)
            selected = 4;
        else if (view.getId() == R.id.sofa)
            selected = 5;
        else if (view.getId() == R.id.table)
            selected = 6;
    }
}
