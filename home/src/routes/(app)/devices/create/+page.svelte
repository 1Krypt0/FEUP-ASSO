<script lang="ts">
	import type { PageData, ActionData } from './$types';

	export let data: PageData;
	export let form: ActionData;
</script>

<section class="px-5 w-full py-4 md:px-28 md:py-4">
	<h1 class="font-bold pl-20 pt-12 text-5xl">Add a device</h1>
	<section class="w-full h-full">
		<form method="post" class="w-1/4 h-2/3 mx-auto flex flex-col gap-6 justify-center">
			<div class="flex items-center">
				<label for="device-name" class="pr-5">Name</label>
				<input
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
				<select class="flex py-3 rounded-full text-center w-full bg-white" id="device-room">
					<option>aaoeu</option>
				</select>
			</div>
			<button
				type="submit"
				class="self-end px-8 mx-5 text-center text-white font-bold my-12 py-3 rounded-full bg-primary"
				>Create</button
			>
		</form>
	</section>
</section>
