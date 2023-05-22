<script lang="ts">
	import type { PageData } from './$types';
	import type { ActionData } from './$types';

	export let form: ActionData;
	export let data: PageData;
</script>

<div class="flex flex-col w-full">
	<h1 class="font-bold pl-20 pt-12 text-5xl">Edit {data.room.name}</h1>
	<section class="w-full h-full">
		<form method="post" class="w-1/4 h-2/3 mx-auto flex flex-col items-center justify-center">
			<div class="flex items-center">
				<label for="device-name" class="pr-5">Name</label>
				<input
					id="device-name"
					name="name"
					type="text"
					placeholder="Enter the new Room name here"
					value={data.room.name}
					class="py-3 px-5 rounded-full"
				/>
			</div>
			{#if form?.missing}
				<p class="pt-2 pr-8">Name field is required</p>
			{/if}
			<button
				type="submit"
				class="self-end px-8 mx-5 text-center text-white font-bold my-12 py-3 rounded-full bg-primary"
				>Edit</button
			>
		</form>
	</section>
</div>
