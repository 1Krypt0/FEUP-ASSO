/** @type {import('tailwindcss').Config} */

module.exports = {
	content: ['./src/**/*.{html,js,svelte,ts}'],
	darkMode: 'class',
	theme: {
		extend: {
			colors: {
				lights: {
					50: '#fffbeb',
					100: '#fef3c7'
				},
				media: {
					50: '#ecfeff',
					100: '#e0f2fe'
				},
				climate: {
					50: '#f0fdf4',
					100: '#ecfccb'
				}
			}
		}
	},
	plugins: []
};
