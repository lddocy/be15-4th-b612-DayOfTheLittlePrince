import * as THREE from 'three';
import { GLTFLoader } from 'three/examples/jsm/loaders/GLTFLoader.js';
import { EXRLoader } from 'three/examples/jsm/loaders/EXRLoader.js';

export function loadHDRI(path, scene) {
    const exrLoader = new EXRLoader();
    exrLoader.load(path, (texture) => {
        texture.mapping = THREE.EquirectangularReflectionMapping;
        scene.background = texture;
        scene.environment = texture;
    });
}

export function loadGLTF(path, scene, onLoad = null) {
    const loader = new GLTFLoader();
    loader.load(path, (gltf) => {
        const model = gltf.scene;
        if (onLoad) onLoad(model);
        scene.add(model);
    }, undefined, (err) => {
        console.error('GLTF load error:', err);
    });
}
