export default {
    content: [
        './index.html',
        './src/**/*.{vue,js,ts,jsx,tsx}',
    ],
    theme: {
        extend: {
            fontFamily: {
                cafe24: ['Cafe24Oneprettynight', 'sans-serif'],
            },
            colors: {
                'dlp_purple': '#8F87F1',
                'dlp_lavender': '#C68EFD',
                'dlp_pinkPurple': '#E9A5F1',
                'dlp_pink': '#FED2E2',
                'dlp_yellow': '#FFFCCB',
            },
        },
    },
    plugins: [],
}
