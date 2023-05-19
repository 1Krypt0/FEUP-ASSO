<script lang="ts">
	import type { ActionData } from './$types';

	export let form: ActionData;
</script>

<div class="flex flex-col w-full h-full">
	<h1 class="font-bold md:pl-20 pt-12 text-center md:text-start text-5xl">Add a new Room</h1>
	<section class="w-full h-full flex items-center justify-center">
		<form method="post" class="flex flex-col justify-center">
			<div class="pl-5">
				<label for="device-name">Name</label>
				<input
					id="device-name"
					name="name"
					type="text"
					placeholder="Enter the new Room name here"
					class="py-3 px-2 md:px-5 rounded-full mt-5 sm:ml-5"
				/>
			</div>
			{#if form?.missing}
				<p class="pt-2 pr-8">Name field is required</p>
			{/if}
			<button
				type="submit"
				class="self-end px-8 mx-5 text-center text-white font-bold my-12 py-3 rounded-full bg-primary"
				>Create</button
			>
		</form>
	</section>
</div>
