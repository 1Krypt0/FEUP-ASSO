<script lang="ts">
	import { createForm } from 'svelte-forms-lib';

	const { form, errors, handleChange, handleSubmit } = createForm({
		initialValues: {
			username: '',
			email: '',
			password: '',
			confirmPassword: ''
		},
		validate: (values) => {
			const errors = {
				username: '',
				email: '',
				password: '',
				confirmPassword: ''
			};

			if (!values.username) errors.username = 'Username is required';
			if (!values.email) errors.email = 'Email is required';
			if (!values.password) errors.password = 'Password is required';
			if (!values.confirmPassword) errors.confirmPassword = 'You must confirm your password';
			if (values.password !== values.confirmPassword)
				errors.confirmPassword = 'Passwords do not match';

			return errors;
		},
		onSubmit: (values) => {
			// TODO: complete register
			console.log(values);
		}
	});
</script>

<div class="flex justify-center items-center w-full">
	<form on:submit|preventDefault={handleSubmit} class="flex flex-col sm:items-end">
		<h1 class="text-4xl font-bold pb-8">
			<span>Create your </span><span class="text-primary">Home</span>
		</h1>

		<formgroup class="py-2 flex flex-col sm:flex-row sm:items-center">
			<label class="sm:mr-4" for="username">Username</label>
			<input
				class="focus:outline-none rounded-[20px] shadow-md py-1.5 px-2.5"
				type="text"
				id="username"
				on:change={handleChange}
				bind:value={$form.username}
			/>
		</formgroup>
		{#if $errors.username}
			<p class="text-xs text-red-600 font-semibold">{$errors.username}</p>
		{/if}

		<formgroup class="py-2 flex flex-col sm:flex-row sm:items-center">
			<label class="sm:mr-4" for="email">Email</label>
			<input
				class="focus:outline-none rounded-[20px] shadow-md py-1.5 px-2.5"
				type="email"
				id="email"
				on:change={handleChange}
				bind:value={$form.email}
			/>
		</formgroup>
		{#if $errors.email}
			<p class="text-xs text-red-600 font-semibold">{$errors.email}</p>
		{/if}

		<formgroup class="py-2 flex flex-col sm:flex-row sm:items-center">
			<label class="sm:mr-4" for="password">Password</label>
			<input
				class="focus:outline-none rounded-[20px] shadow-md py-1.5 px-2.5"
				type="password"
				id="password"
				on:change={handleChange}
				bind:value={$form.password}
			/>
		</formgroup>
		{#if $errors.password}
			<p class="text-xs text-red-600 font-semibold">{$errors.password}</p>
		{/if}

		<formgroup class="py-2 flex flex-col sm:flex-row sm:items-center">
			<label class="sm:mr-4" for="confirmPassword">Confirm Password</label>
			<input
				class="focus:outline-none rounded-[20px] shadow-md py-1.5 px-2.5"
				type="password"
				id="confirmPassword"
				on:change={handleChange}
				bind:value={$form.confirmPassword}
			/>
		</formgroup>
		{#if $errors.confirmPassword}
			<p class="text-xs text-red-600 font-semibold">{$errors.confirmPassword}</p>
		{/if}

		<div class="mt-4">
			<span
				>Already have an account? <a
					href="/login"
					class="underline text-gray-400 hover:text-gray-500 mr-4">Login</a
				></span
			>
			<button type="submit" class="bg-primary text-white font-bold rounded-full py-3 px-8"
				>Register</button
			>
		</div>
	</form>
</div>
