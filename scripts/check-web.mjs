import { readFileSync } from 'node:fs';
import assert from 'node:assert/strict';

const web = readFileSync('web/index.html', 'utf8');
const tizen = readFileSync('samsung-tizen/index.html', 'utf8');
const android = readFileSync('android-tv/app/src/main/assets/index.html', 'utf8');
const standalone = readFileSync('standalone/Deichlers_Live_Screensaver.html', 'utf8');
const scene = readFileSync('web/assets/scene.webp');
const tizenScene = readFileSync('samsung-tizen/assets/scene.webp');
const androidScene = readFileSync('android-tv/app/src/main/assets/assets/scene.webp');

assert.equal(web, tizen, 'Tizen HTML should match shared browser renderer');
assert.equal(web, android, 'Android HTML should match shared browser renderer');
assert(scene.equals(tizenScene), 'Tizen asset should match shared artwork');
assert(scene.equals(androidScene), 'Android asset should match shared artwork');
assert.equal(scene.subarray(0, 4).toString(), 'RIFF');
assert.equal(scene.subarray(8, 12).toString(), 'WEBP');

const b64 = standalone.match(/src="data:image\/webp;base64,([A-Za-z0-9+/=]+)"/)?.[1];
assert(b64, 'Standalone HTML must embed WebP scene');
assert(Buffer.from(b64, 'base64').equals(scene), 'Embedded artwork must match shared file');
assert.equal(standalone.replace(/src="data:image\/webp;base64,[A-Za-z0-9+/=]+"/, 'src="assets/scene.webp"'), web);

const script = web.match(/<script>([\s\S]*?)<\/script>/)?.[1];
assert(script, 'The clock renderer must have JavaScript');
new Function(script); // Syntax check in Node without accessing a DOM.
assert(script.includes('new Date()'), 'The clock must display live device-local time');
assert(script.includes("setInterval(updateClock,1000)"), 'The clock must update continuously');
assert(web.includes('prefers-reduced-motion'), 'Reduced-motion preference should be respected');

console.log('Web, Android and Tizen files are consistent; 4K WebP is embedded correctly; clock script syntax is valid.');
