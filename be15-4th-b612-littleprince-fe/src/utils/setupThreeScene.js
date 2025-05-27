import * as THREE from 'three';

export function createRenderer(container) {
    const renderer =
        new THREE.WebGLRenderer({ antialias: true, alpha: true, preserveDrawingBuffer: true });
    renderer.setSize(container.clientWidth, container.clientHeight);
    return renderer;
}

export function createCamera(container) {
    const aspect = container.clientWidth / container.clientHeight;
    const camera = new THREE.PerspectiveCamera(60, aspect, 0.1, 1000);
    camera.position.set(-5, 40, 30);
    camera.lookAt(0, 0, 0);
    return camera;
}

export function addBasicLighting(scene) {
    const light = new THREE.DirectionalLight(0xffffff, 1);
    light.position.set(1, 1, 1).normalize();
    scene.add(light);

    const ambient = new THREE.AmbientLight(0xffffff, 0.5);
    scene.add(ambient);
}
