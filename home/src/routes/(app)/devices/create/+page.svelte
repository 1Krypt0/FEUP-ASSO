<script lang="ts">
	import { goto } from '$app/navigation';
	import type { PageData, ActionData } from './$types';

	export let data: PageData;
	export let form: ActionData;
	let step = 0;
	let formData = {
		name: '',
		roomID: '',
		categoryID: '',
		selectedName: ''
	};

	async function submit() {
		const response = await fetch('/devices/create', {
			method: 'POST',
			body: JSON.stringify(formData)
		});

		if (response.ok) {
			goto('rooms');
		}
	}
</script>

<section class="px-5 w-full py-4 md:px-28 md:py-4">
	<h1 class="font-bold pl-20 pt-12 text-5xl">Add a device</h1>
	<section class="w-full h-full">
		<form
			method="POST"
			on:submit|preventDefault={submit}
			class="w-1/3 h-2/3 mx-auto flex flex-col gap-6 justify-center"
		>
			{#if step === 0}
				<div class="flex items-center">
					<label for="device-name" class="pr-5">Name</label>
					<input
						bind:value={formData.name}
						id="device-name"
						name="name"
						type="text"
						placeholder="Enter the new device name here"
						class="py-3 px-5 rounded-full w-full"
					/>
				</div>
				{#if form?.missing}
					<p class="pt-2 pr-8">Name field is required</p>
				{/if}

				<div class="flex items-center">
					<label for="device-room" class="pr-5">Room</label>
					<select
						class="flex py-3 rounded-full text-center w-full bg-white"
						id="device-room"
						name="room"
						bind:value={formData.roomID}
					>
						{#each data.rooms as room}
							<option value={room.id}>{room.name}</option>
						{/each}
					</select>
				</div>

				<div class="flex items-center">
					<label for="device-category" class="pr-5">Category</label>
					<select
						class="flex py-3 rounded-full text-center w-full bg-white"
						id="device-category"
						name="category"
						bind:value={formData.categoryID}
					>
						{#each data.categories as category}
							<option value={category.id}>{category.name}</option>
						{/each}
					</select>
				</div>
				<button
					type="button"
					class="self-end px-8 mx-5 text-center text-white font-bold my-12 py-3 rounded-full bg-primary"
					on:click={() => step++}>Search</button
				>
			{:else if step === 1}
				<h2 class="font-bold text-3xl">Search Results</h2>

				<section class="flex flex-col gap-6 overflow-auto py-6">
					{#each data.foundDevices as result}
						<button
							type="button"
							class="bg-white drop-shadow-lg rounded-full text-center text-lg py-3 w-2/3 focus:bg-accent focus:text-white"
							on:click={() => (formData.selectedName = result.name)}
						>
							{result.name}
						</button>
					{/each}
				</section>
				<button
					type="submit"
					class="self-end px-8 mx-5 text-center text-white font-bold my-6 py-3 rounded-full bg-primary"
					>Create</button
				>
			{/if}
		</form>
	</section>
</section>
