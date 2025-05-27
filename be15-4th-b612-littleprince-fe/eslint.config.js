import { defineConfig, globalIgnores } from 'eslint/config';
import globals from 'globals';
import js from '@eslint/js';
import pluginVue from 'eslint-plugin-vue';
import skipFormatting from '@vue/eslint-config-prettier/skip-formatting';

export default defineConfig([
    {
        name: 'app/files-to-lint',
        files: ['**/*.{js,mjs,jsx,vue}'],
    },

    globalIgnores(['**/dist/**', '**/dist-ssr/**', '**/coverage/**']),

    {
        languageOptions: {
            globals: {
                ...globals.browser,
            },
        },
        rules: {
            quotes: ['error', 'single'], // 작은 따옴표
            semi: ['error', 'always'], // 세미콜론
        },
    },

    js.configs.recommended,
    ...pluginVue.configs['flat/essential'],
    skipFormatting,
]);
