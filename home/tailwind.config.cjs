/** @type {import('tailwindcss').Config} */

module.exports = {
	content: ['./src/**/*.{html,js,svelte,ts}'],
	darkMode: 'class',
	theme: {
		extend: {
			colors: {
				light: '#f4f4f4',
				primary: '#17a2b8',
				secondary: '#6c757d',
				accent: '#ffcc80',
				danger: '#dc3545',
				success: '#28a745'
			},
			fontFamily: {
				poppins: ['Poppins', 'sans-serif']
			}
		}
	},
	plugins: []
};
