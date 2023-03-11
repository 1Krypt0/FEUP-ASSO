/** @type {import('tailwindcss').Config} */

module.exports = {
	content: ['./src/**/*.{html,js,svelte,ts}'],
	darkMode: 'class',
	theme: {
		extend: {
			colors: {
				lights: {
					50: '#fef8e0',
					100: '#fde99f'
				},
				media: {
					50: '#dbedfc',
					100: '#8fc6f6'
				},
				climate: {
					50: '#e5f5dd',
					100: '#ace194'
				}
			}
		}
	},
	plugins: []
};
