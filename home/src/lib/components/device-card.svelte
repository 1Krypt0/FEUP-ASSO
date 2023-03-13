<script lang="ts">
	import { page } from '$app/stores';

	export let device: { id: string; name: string };
	export let data: any;

	console.log(data);
</script>

<div class="h-full w-full p-6 bg-white rounded-lg shadow-md">
	<h5 class="mb-2 text-2xl font-bold">
		{device.name}
	</h5>
	<div class="flex justify-end">
		<a
			href={`${$page.url}/${device.id}`}
			class="py-2 px-4 text-white hover:font-bold rounded-full {data.colors[data.page][400]}"
		>
			See more
		</a>
	</div>
</div>
