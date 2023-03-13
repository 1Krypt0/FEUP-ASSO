<script lang="ts">
	import { goto } from '$app/navigation';
	import { createDevice } from '$lib/services/devices';

	export let data;

	let name = '';
	let macAddress = '';

	async function addDevice() {
		await createDevice({
			name: name,
			macAddress: macAddress,
			type: data.type[data.page]
		});
		goto(`/${data.page}`);
	}
</script>

<svelte:head>
	<title>Home by Iota - Add {data.title}</title>
	<meta name="description" content="Add {data.title} Page" />
</svelte:head>

<section class="flex flex-col justify-center items-center w-full h-full">
	<h1 class="text-4xl font-bold mb-8">Add a device</h1>
	<form on:submit|preventDefault={addDevice} class="flex flex-col sm:items-end">
		<formgroup class="py-2 flex flex-col sm:flex-row sm:items-center">
			<label class="sm:mr-4" for="name">Name</label>
			<input
				class="focus:outline-none rounded-[20px] shadow-md py-1.5 px-2.5"
				type="text"
				id="name"
				bind:value={name}
			/>
		</formgroup>

		<formgroup class="py-2 flex flex-col sm:flex-row sm:items-center">
			<label class="sm:mr-4" for="macaddr">MAC Address</label>
			<input
				class="focus:outline-none rounded-[20px] shadow-md py-1.5 px-2.5"
				type="text"
				id="macaddr"
				bind:value={macAddress}
			/>
		</formgroup>

		<div class="flex justify-end w-full mt-4">
			<button
				type="submit"
				class="{data.colors[data.page][400]} hover:{data.colors[
					data.page
				][500]} text-white font-bold rounded-full py-2 px-4">Add</button
			>
		</div>
	</form>
</section>
