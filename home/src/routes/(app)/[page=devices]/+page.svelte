<script lang="ts">
	import type { PageData } from './$types';

	export let data: PageData;
</script>

<svelte:head>
	<title>Home by Iota</title>
	<meta name="description" content="Home page" />
</svelte:head>

<section
	class="bg-right-bottom bg-contain bg-no-repeat w-full {data.colors.pageColors[data.page]} "
	style:background-image={data.bg_img}
/>
