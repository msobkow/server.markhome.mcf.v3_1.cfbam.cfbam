// Description: Java 25 Table Object implementation for SecClusRole.

/*
 *	server.markhome.mcf.CFBam
 *
 *	Copyright (c) 2016-2026 Mark Stephen Sobkow
 *	
 *	Mark's Code Fractal CFBam 3.1 Business Application Model
 *	
 *	Copyright 2016-2026 Mark Stephen Sobkow
 *	
 *	This file is part of Mark's Code Fractal CFBam.
 *	
 *	Mark's Code Fractal CFBam is available under dual commercial license from
 *	Mark Stephen Sobkow, or under the terms of the GNU General Public License,
 *	Version 3 or later with classpath and static linking exceptions.
 *	
 *	As a special exception, Mark Sobkow gives you permission to link this library
 *	with independent modules to produce an executable, provided that none of them
 *	conflict with the intent of the GPLv3; that is, you are not allowed to invoke
 *	the methods of this library from non-GPLv3-compatibly licensed code. You may not
 *	implement an LPGLv3 "wedge" to try to bypass this restriction. That said, code which
 *	does not rely on this library is free to specify whatever license its authors decide
 *	to use. Mark Sobkow specifically rejects the infectious nature of the GPLv3, and
 *	considers the mere act of including GPLv3 modules in an executable to be perfectly
 *	reasonable given tools like modern Java's single-jar deployment options.
 *	
 *	Mark's Code Fractal CFBam is free software: you can redistribute it and/or
 *	modify it under the terms of the GNU General Public License as published by
 *	the Free Software Foundation, either version 3 of the License, or
 *	(at your option) any later version.
 *	
 *	Mark's Code Fractal CFBam is distributed in the hope that it will be useful,
 *	but WITHOUT ANY WARRANTY; without even the implied warranty of
 *	MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *	GNU General Public License for more details.
 *	
 *	You should have received a copy of the GNU General Public License
 *	along with Mark's Code Fractal CFBam.  If not, see <https://www.gnu.org/licenses/>.
 *	
 *	If you wish to modify and use this code without publishing your changes,
 *	or integrate it with proprietary code, please contact Mark Stephen Sobkow
 *	for a commercial license at mark.sobkow@gmail.com
 */

package server.markhome.mcf.v3_1.cfbam.cfbamobj;

import java.math.*;
import java.sql.*;
import java.text.*;
import java.time.*;
import java.util.*;
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.text.StringEscapeUtils;
import server.markhome.mcf.v3_1.cflib.*;
import server.markhome.mcf.v3_1.cflib.dbutil.*;
import server.markhome.mcf.v3_1.cfsec.cfsec.*;
import server.markhome.mcf.v3_1.cfint.cfint.*;
import server.markhome.mcf.v3_1.cfsec.cfsecobj.*;
import server.markhome.mcf.v3_1.cfint.cfintobj.*;
import server.markhome.mcf.v3_1.cfsec.cfsec.*;

public class CFBamSecClusRoleTableObj
	implements ICFBamSecClusRoleTableObj
{
	protected ICFBamSchemaObj schema;
	private Map<CFLibDbKeyHash256, ICFSecSecClusRoleObj> members;
	private Map<CFLibDbKeyHash256, ICFSecSecClusRoleObj> allSecClusRole;
	private Map< ICFSecSecClusRoleByClusterIdxKey,
		Map<CFLibDbKeyHash256, ICFSecSecClusRoleObj > > indexByClusterIdx;
	private Map< ICFSecSecClusRoleByNameIdxKey,
		Map<CFLibDbKeyHash256, ICFSecSecClusRoleObj > > indexByNameIdx;
	private Map< ICFSecSecClusRoleByUNameIdxKey,
		ICFSecSecClusRoleObj > indexByUNameIdx;
	public static String TABLE_NAME = "SecClusRole";
	public static String TABLE_DBNAME = "secclusrole";

	public CFBamSecClusRoleTableObj() {
		schema = null;
		members = new HashMap<CFLibDbKeyHash256, ICFSecSecClusRoleObj>();
		allSecClusRole = null;
		indexByClusterIdx = null;
		indexByNameIdx = null;
		indexByUNameIdx = null;
	}

	public CFBamSecClusRoleTableObj( ICFSecSchemaObj argSchema ) {
		schema = (ICFBamSchemaObj)argSchema;
		members = new HashMap<CFLibDbKeyHash256, ICFSecSecClusRoleObj>();
		allSecClusRole = null;
		indexByClusterIdx = null;
		indexByNameIdx = null;
		indexByUNameIdx = null;
	}
	
	/**
	 *	Get class code always returns the runtime class code for the objects, which is not stable until the application is done initializing and registering its objects.
	 *
	 *	@return runtime classcode
	 */ 
	@Override
	public int getClassCode() {
		return CFSecSecClusRoleTableObj.getRuntimeClassCode();
	}	

	/**
	 *	Get the backing store schema's class code, which is hard-coded into the object hierarchy.
	 *
	 *	@return The hardcoded backing store class code for this object, which is only valid in that schema.
	 */
	public static int getBackingClassCode() {
		return( CFSecSecClusRoleTableObj.getBackingClassCode() );
	}

	/**
	 *	Get the runtime class code for this table; this value is only stable after the application is fully initialized.
	 *
	 *	@return runtimeClassCode
	 */
	public static int getRuntimeClassCode() {
		return( CFSecSecClusRoleTableObj.getRuntimeClassCode() );
	}

	@Override
	public ICFSecSchemaObj getSchema() {
		return( schema );
	}

	@Override
	public void setSchema( ICFSecSchemaObj value ) {
		schema = (ICFBamSchemaObj)value;
	}

	@Override
	public String getTableName() {
		return( TABLE_NAME );
	}

	@Override
	public String getTableDbName() {
		return( TABLE_DBNAME );
	}

	@Override
	public Class getObjQualifyingClass() {
		return( null );
	}


	@Override
	public void minimizeMemory() {
		allSecClusRole = null;
		indexByClusterIdx = null;
		indexByNameIdx = null;
		indexByUNameIdx = null;
		List<ICFSecSecClusRoleObj> toForget = new LinkedList<ICFSecSecClusRoleObj>();
		ICFSecSecClusRoleObj cur = null;
		Iterator<ICFSecSecClusRoleObj> iter = members.values().iterator();
		while( iter.hasNext() ) {
			cur = iter.next();
			toForget.add( cur );
		}
		iter = toForget.iterator();
		while( iter.hasNext() ) {
			cur = iter.next();
			cur.forget();
		}
	}
	/**
	 *	If your implementation subclasses the objects,
	 *	you'll want to overload the constructByClassCode()
	 *	implementation to return your implementation's
	 *	instances instead of the base implementation.
	 *
	 *	This is the sole factory for instances derived from
	 *	CFBamSecClusRoleObj.
	 */
	@Override
	public ICFSecSecClusRoleObj newInstance() {
		ICFSecSecClusRoleObj inst = new CFBamSecClusRoleObj( schema );
		return( inst );
	}

	/**
	 *	If your implementation subclasses the objects,
	 *	you'll want to overload the constructByClassCode()
	 *	implementation to return your implementation's
	 *	instances instead of the base implementation.
	 *
	 *	This is the sole factory for instances derived from
	 *	CFBamSecClusRoleObj.
	 */
	@Override
	public ICFSecSecClusRoleEditObj newEditInstance( ICFSecSecClusRoleObj orig ) {
		ICFSecSecClusRoleEditObj edit = new CFBamSecClusRoleEditObj( orig );
		return( edit );
	}

	@Override
	public ICFSecSecClusRoleObj realiseSecClusRole( ICFSecSecClusRoleObj Obj ) {
		ICFSecSecClusRoleObj obj = Obj;
		CFLibDbKeyHash256 pkey = obj.getPKey();
		ICFSecSecClusRoleObj keepObj = null;
		if( members.containsKey( pkey ) && ( null != members.get( pkey ) ) ) {
			ICFSecSecClusRoleObj existingObj = members.get( pkey );
			keepObj = existingObj;

			/*
			 *	We always rebind the data because if we're being called, some index has
			 *	been updated and is refreshing it's data, which may or may not have changed
			 */

			// Detach object from alternate and duplicate indexes, leave PKey alone

			if( indexByClusterIdx != null ) {
				ICFSecSecClusRoleByClusterIdxKey keyClusterIdx =
					schema.getCFSecBackingStore().getFactorySecClusRole().newByClusterIdxKey();
				keyClusterIdx.setRequiredClusterId( keepObj.getRequiredClusterId() );
				Map<CFLibDbKeyHash256, ICFSecSecClusRoleObj > mapClusterIdx = indexByClusterIdx.get( keyClusterIdx );
				if( mapClusterIdx != null ) {
					mapClusterIdx.remove( keepObj.getPKey() );
					if( mapClusterIdx.size() <= 0 ) {
						indexByClusterIdx.remove( keyClusterIdx );
					}
				}
			}

			if( indexByNameIdx != null ) {
				ICFSecSecClusRoleByNameIdxKey keyNameIdx =
					schema.getCFSecBackingStore().getFactorySecClusRole().newByNameIdxKey();
				keyNameIdx.setRequiredName( keepObj.getRequiredName() );
				Map<CFLibDbKeyHash256, ICFSecSecClusRoleObj > mapNameIdx = indexByNameIdx.get( keyNameIdx );
				if( mapNameIdx != null ) {
					mapNameIdx.remove( keepObj.getPKey() );
					if( mapNameIdx.size() <= 0 ) {
						indexByNameIdx.remove( keyNameIdx );
					}
				}
			}

			if( indexByUNameIdx != null ) {
				ICFSecSecClusRoleByUNameIdxKey keyUNameIdx =
					schema.getCFSecBackingStore().getFactorySecClusRole().newByUNameIdxKey();
				keyUNameIdx.setRequiredClusterId( keepObj.getRequiredClusterId() );
				keyUNameIdx.setRequiredName( keepObj.getRequiredName() );
				indexByUNameIdx.remove( keyUNameIdx );
			}

			keepObj.setRec( Obj.getRec() );
			// Attach new object to alternate and duplicate indexes -- PKey stay stable

			if( indexByClusterIdx != null ) {
				ICFSecSecClusRoleByClusterIdxKey keyClusterIdx =
					schema.getCFSecBackingStore().getFactorySecClusRole().newByClusterIdxKey();
				keyClusterIdx.setRequiredClusterId( keepObj.getRequiredClusterId() );
				Map<CFLibDbKeyHash256, ICFSecSecClusRoleObj > mapClusterIdx = indexByClusterIdx.get( keyClusterIdx );
				if( mapClusterIdx != null ) {
					mapClusterIdx.put( keepObj.getPKey(), keepObj );
				}
			}

			if( indexByNameIdx != null ) {
				ICFSecSecClusRoleByNameIdxKey keyNameIdx =
					schema.getCFSecBackingStore().getFactorySecClusRole().newByNameIdxKey();
				keyNameIdx.setRequiredName( keepObj.getRequiredName() );
				Map<CFLibDbKeyHash256, ICFSecSecClusRoleObj > mapNameIdx = indexByNameIdx.get( keyNameIdx );
				if( mapNameIdx != null ) {
					mapNameIdx.put( keepObj.getPKey(), keepObj );
				}
			}

			if( indexByUNameIdx != null ) {
				ICFSecSecClusRoleByUNameIdxKey keyUNameIdx =
					schema.getCFSecBackingStore().getFactorySecClusRole().newByUNameIdxKey();
				keyUNameIdx.setRequiredClusterId( keepObj.getRequiredClusterId() );
				keyUNameIdx.setRequiredName( keepObj.getRequiredName() );
				indexByUNameIdx.put( keyUNameIdx, keepObj );
			}

			if( allSecClusRole != null ) {
				allSecClusRole.put( keepObj.getPKey(), keepObj );
			}
		}
		else {
			keepObj = obj;
			keepObj.setIsNew( false );

			// Attach new object to PKey, all, alternate, and duplicate indexes
			members.put( keepObj.getPKey(), keepObj );
			if( allSecClusRole != null ) {
				allSecClusRole.put( keepObj.getPKey(), keepObj );
			}

			if( indexByClusterIdx != null ) {
				ICFSecSecClusRoleByClusterIdxKey keyClusterIdx =
					schema.getCFSecBackingStore().getFactorySecClusRole().newByClusterIdxKey();
				keyClusterIdx.setRequiredClusterId( keepObj.getRequiredClusterId() );
				Map<CFLibDbKeyHash256, ICFSecSecClusRoleObj > mapClusterIdx = indexByClusterIdx.get( keyClusterIdx );
				if( mapClusterIdx != null ) {
					mapClusterIdx.put( keepObj.getPKey(), keepObj );
				}
			}

			if( indexByNameIdx != null ) {
				ICFSecSecClusRoleByNameIdxKey keyNameIdx =
					schema.getCFSecBackingStore().getFactorySecClusRole().newByNameIdxKey();
				keyNameIdx.setRequiredName( keepObj.getRequiredName() );
				Map<CFLibDbKeyHash256, ICFSecSecClusRoleObj > mapNameIdx = indexByNameIdx.get( keyNameIdx );
				if( mapNameIdx != null ) {
					mapNameIdx.put( keepObj.getPKey(), keepObj );
				}
			}

			if( indexByUNameIdx != null ) {
				ICFSecSecClusRoleByUNameIdxKey keyUNameIdx =
					schema.getCFSecBackingStore().getFactorySecClusRole().newByUNameIdxKey();
				keyUNameIdx.setRequiredClusterId( keepObj.getRequiredClusterId() );
				keyUNameIdx.setRequiredName( keepObj.getRequiredName() );
				indexByUNameIdx.put( keyUNameIdx, keepObj );
			}

		}
		return( keepObj );
	}

	@Override
	public ICFSecSecClusRoleObj createSecClusRole( ICFSecSecClusRoleObj Obj ) {
		ICFSecSecClusRoleObj obj = Obj;
		ICFSecSecClusRole rec = obj.getSecClusRoleRec();
		schema.getCFSecBackingStore().getTableSecClusRole().createSecClusRole(
			null,
			rec );
		obj.copyRecToPKey();
		obj = obj.realise();
		obj.endEdit();
		return( obj );
	}

	@Override
	public ICFSecSecClusRoleObj readSecClusRole( CFLibDbKeyHash256 pkey ) {
		return( readSecClusRole( pkey, false ) );
	}

	@Override
	public ICFSecSecClusRoleObj readSecClusRole( CFLibDbKeyHash256 pkey, boolean forceRead ) {
		ICFSecSecClusRoleObj obj = null;
		if( ( ! forceRead ) && members.containsKey( pkey ) ) {
			obj = members.get( pkey );
		}
		else {
			ICFSecSecClusRole readRec = schema.getCFSecBackingStore().getTableSecClusRole().readDerivedByIdIdx( null,
						pkey );
			if( readRec != null ) {
				obj = schema.getSecClusRoleTableObj().newInstance();
				obj.setPKey( readRec.getPKey() );
				obj.setRec( readRec );
				obj = (ICFSecSecClusRoleObj)obj.realise();
			}
		}
		return( obj );
	}

	@Override
	public ICFSecSecClusRoleObj readCachedSecClusRole( CFLibDbKeyHash256 pkey ) {
		ICFSecSecClusRoleObj obj = null;
		if( members.containsKey( pkey ) ) {
			obj = members.get( pkey );
		}
		return( obj );
	}

	@Override
	public void reallyDeepDisposeSecClusRole( ICFSecSecClusRoleObj obj )
	{
		final String S_ProcName = "CFBamSecClusRoleTableObj.reallyDeepDisposeSecClusRole() ";
		String classCode;
		if( obj == null ) {
			return;
		}
		CFLibDbKeyHash256 pkey = obj.getPKey();
		ICFSecSecClusRoleObj existing = readCachedSecClusRole( pkey );
		if( existing == null ) {
			return;
		}
		members.remove( pkey );
		ICFSecSecClusRoleByClusterIdxKey keyClusterIdx = schema.getCFSecBackingStore().getFactorySecClusRole().newByClusterIdxKey();
		keyClusterIdx.setRequiredClusterId( existing.getRequiredClusterId() );

		ICFSecSecClusRoleByNameIdxKey keyNameIdx = schema.getCFSecBackingStore().getFactorySecClusRole().newByNameIdxKey();
		keyNameIdx.setRequiredName( existing.getRequiredName() );

		ICFSecSecClusRoleByUNameIdxKey keyUNameIdx = schema.getCFSecBackingStore().getFactorySecClusRole().newByUNameIdxKey();
		keyUNameIdx.setRequiredClusterId( existing.getRequiredClusterId() );
		keyUNameIdx.setRequiredName( existing.getRequiredName() );



		if( indexByClusterIdx != null ) {
			if( indexByClusterIdx.containsKey( keyClusterIdx ) ) {
				indexByClusterIdx.get( keyClusterIdx ).remove( pkey );
				if( indexByClusterIdx.get( keyClusterIdx ).size() <= 0 ) {
					indexByClusterIdx.remove( keyClusterIdx );
				}
			}
		}

		if( indexByNameIdx != null ) {
			if( indexByNameIdx.containsKey( keyNameIdx ) ) {
				indexByNameIdx.get( keyNameIdx ).remove( pkey );
				if( indexByNameIdx.get( keyNameIdx ).size() <= 0 ) {
					indexByNameIdx.remove( keyNameIdx );
				}
			}
		}

		if( indexByUNameIdx != null ) {
			indexByUNameIdx.remove( keyUNameIdx );
		}


	}
	@Override
	public void deepDisposeSecClusRole( CFLibDbKeyHash256 pkey ) {
		ICFSecSecClusRoleObj obj = readCachedSecClusRole( pkey );
		if( obj != null ) {
			obj.forget();
		}
	}

	@Override
	public ICFSecSecClusRoleObj lockSecClusRole( CFLibDbKeyHash256 pkey ) {
		ICFSecSecClusRoleObj locked = null;
		ICFSecSecClusRole lockRec = schema.getCFSecBackingStore().getTableSecClusRole().lockDerived( null, pkey );
		if( lockRec != null ) {
				locked = schema.getSecClusRoleTableObj().newInstance();
			locked.setRec( lockRec );
			locked.setPKey( lockRec.getPKey() );
			locked = (ICFSecSecClusRoleObj)locked.realise();
		}
		else {
			throw new CFLibCollisionDetectedException( getClass(), "lockSecClusRole", pkey );
		}
		return( locked );
	}

	@Override
	public List<ICFSecSecClusRoleObj> readAllSecClusRole() {
		return( readAllSecClusRole( false ) );
	}

	@Override
	public List<ICFSecSecClusRoleObj> readAllSecClusRole( boolean forceRead ) {
		final String S_ProcName = "readAllSecClusRole";
		if( ( allSecClusRole == null ) || forceRead ) {
			Map<CFLibDbKeyHash256, ICFSecSecClusRoleObj> map = new HashMap<CFLibDbKeyHash256,ICFSecSecClusRoleObj>();
			allSecClusRole = map;
			ICFSecSecClusRole[] recList = schema.getCFSecBackingStore().getTableSecClusRole().readAllDerived( null );
			ICFSecSecClusRole rec;
			ICFSecSecClusRoleObj obj;
			for( int idx = 0; idx < recList.length; idx ++ ) {
				rec = recList[ idx ];
				obj = newInstance();
				obj.setPKey( rec.getPKey() );
				obj.setRec( rec );
				ICFSecSecClusRoleObj realised = (ICFSecSecClusRoleObj)obj.realise();
			}
		}
		int len = allSecClusRole.size();
		ICFSecSecClusRoleObj arr[] = new ICFSecSecClusRoleObj[len];
		Iterator<ICFSecSecClusRoleObj> valIter = allSecClusRole.values().iterator();
		int idx = 0;
		while( ( idx < len ) && valIter.hasNext() ) {
			arr[idx++] = valIter.next();
		}
		if( idx < len ) {
			throw new CFLibArgumentUnderflowException( getClass(),
				S_ProcName,
				0,
				"idx",
				idx,
				len );
		}
		else if( valIter.hasNext() ) {
			throw new CFLibArgumentOverflowException( getClass(),
					S_ProcName,
					0,
					"idx",
					idx,
					len );
		}
		ArrayList<ICFSecSecClusRoleObj> arrayList = new ArrayList<ICFSecSecClusRoleObj>(len);
		for( idx = 0; idx < len; idx ++ ) {
			arrayList.add( arr[idx] );
		}

		Comparator<ICFSecSecClusRoleObj> cmp = new Comparator<ICFSecSecClusRoleObj>() {
			@Override
			public int compare( ICFSecSecClusRoleObj lhs, ICFSecSecClusRoleObj rhs ) {
				if( lhs == null ) {
					if( rhs == null ) {
						return( 0 );
					}
					else {
						return( -1 );
					}
				}
				else if( rhs == null ) {
					return( 1 );
				}
				else {
					CFLibDbKeyHash256 lhsPKey = lhs.getPKey();
					CFLibDbKeyHash256 rhsPKey = rhs.getPKey();
					int ret = lhsPKey.compareTo( rhsPKey );
					return( ret );
				}
			}
		};
		Collections.sort( arrayList, cmp );
		List<ICFSecSecClusRoleObj> sortedList = arrayList;
		return( sortedList );
	}

	@Override
	public List<ICFSecSecClusRoleObj> readCachedAllSecClusRole() {
		final String S_ProcName = "readCachedAllSecClusRole";
		ArrayList<ICFSecSecClusRoleObj> arrayList = new ArrayList<ICFSecSecClusRoleObj>();
		if( allSecClusRole != null ) {
			int len = allSecClusRole.size();
			ICFSecSecClusRoleObj arr[] = new ICFSecSecClusRoleObj[len];
			Iterator<ICFSecSecClusRoleObj> valIter = allSecClusRole.values().iterator();
			int idx = 0;
			while( ( idx < len ) && valIter.hasNext() ) {
				arr[idx++] = valIter.next();
			}
			if( idx < len ) {
				throw new CFLibArgumentUnderflowException( getClass(),
					S_ProcName,
					0,
					"idx",
					idx,
					len );
			}
			else if( valIter.hasNext() ) {
				throw new CFLibArgumentOverflowException( getClass(),
						S_ProcName,
						0,
						"idx",
						idx,
						len );
			}
			for( idx = 0; idx < len; idx ++ ) {
				arrayList.add( arr[idx] );
			}
		}
		Comparator<ICFSecSecClusRoleObj> cmp = new Comparator<ICFSecSecClusRoleObj>() {
			public int compare( ICFSecSecClusRoleObj lhs, ICFSecSecClusRoleObj rhs ) {
				if( lhs == null ) {
					if( rhs == null ) {
						return( 0 );
					}
					else {
						return( -1 );
					}
				}
				else if( rhs == null ) {
					return( 1 );
				}
				else {
					CFLibDbKeyHash256 lhsPKey = lhs.getPKey();
					CFLibDbKeyHash256 rhsPKey = rhs.getPKey();
					int ret = lhsPKey.compareTo( rhsPKey );
					return( ret );
				}
			}
		};
		Collections.sort( arrayList, cmp );
		return( arrayList );
	}

	@Override
	public ICFSecSecClusRoleObj readSecClusRoleByIdIdx( CFLibDbKeyHash256 SecClusRoleId )
	{
		return( readSecClusRoleByIdIdx( SecClusRoleId,
			false ) );
	}

	@Override
	public ICFSecSecClusRoleObj readSecClusRoleByIdIdx( CFLibDbKeyHash256 SecClusRoleId, boolean forceRead )
	{
		ICFSecSecClusRoleObj obj = readSecClusRole( SecClusRoleId, forceRead );
		return( obj );
	}

	@Override
	public List<ICFSecSecClusRoleObj> readSecClusRoleByClusterIdx( CFLibDbKeyHash256 ClusterId )
	{
		return( readSecClusRoleByClusterIdx( ClusterId,
			false ) );
	}

	@Override
	public List<ICFSecSecClusRoleObj> readSecClusRoleByClusterIdx( CFLibDbKeyHash256 ClusterId,
		boolean forceRead )
	{
		final String S_ProcName = "readSecClusRoleByClusterIdx";
		ICFSecSecClusRoleByClusterIdxKey key = schema.getCFSecBackingStore().getFactorySecClusRole().newByClusterIdxKey();
		key.setRequiredClusterId( ClusterId );
		Map<CFLibDbKeyHash256, ICFSecSecClusRoleObj> dict;
		if( indexByClusterIdx == null ) {
			indexByClusterIdx = new HashMap< ICFSecSecClusRoleByClusterIdxKey,
				Map< CFLibDbKeyHash256, ICFSecSecClusRoleObj > >();
		}
		if( ( ! forceRead ) && indexByClusterIdx.containsKey( key ) ) {
			dict = indexByClusterIdx.get( key );
		}
		else {
			dict = new HashMap<CFLibDbKeyHash256, ICFSecSecClusRoleObj>();
			ICFSecSecClusRoleObj obj;
			ICFSecSecClusRole[] recList = schema.getCFSecBackingStore().getTableSecClusRole().readDerivedByClusterIdx( null,
				ClusterId );
			ICFSecSecClusRole rec;
			for( int idx = 0; idx < recList.length; idx ++ ) {
				rec = recList[ idx ];
				obj = schema.getSecClusRoleTableObj().newInstance();
				obj.setPKey( rec.getPKey() );
				obj.setRec( rec );
				ICFSecSecClusRoleObj realised = (ICFSecSecClusRoleObj)obj.realise();
				dict.put( realised.getPKey(), realised );
			}
			indexByClusterIdx.put( key, dict );
		}
		int len = dict.size();
		ICFSecSecClusRoleObj arr[] = new ICFSecSecClusRoleObj[len];
		Iterator<ICFSecSecClusRoleObj> valIter = dict.values().iterator();
		int idx = 0;
		while( ( idx < len ) && valIter.hasNext() ) {
			arr[idx++] = valIter.next();
		}
		if( idx < len ) {
			throw new CFLibArgumentUnderflowException( getClass(),
				S_ProcName,
				0,
				"idx",
				idx,
				len );
		}
		else if( valIter.hasNext() ) {
			throw new CFLibArgumentOverflowException( getClass(),
					S_ProcName,
					0,
					"idx",
					idx,
					len );
		}
		ArrayList<ICFSecSecClusRoleObj> arrayList = new ArrayList<ICFSecSecClusRoleObj>(len);
		for( idx = 0; idx < len; idx ++ ) {
			arrayList.add( arr[idx] );
		}

		Comparator<ICFSecSecClusRoleObj> cmp = new Comparator<ICFSecSecClusRoleObj>() {
			@Override
			public int compare( ICFSecSecClusRoleObj lhs, ICFSecSecClusRoleObj rhs ) {
				if( lhs == null ) {
					if( rhs == null ) {
						return( 0 );
					}
					else {
						return( -1 );
					}
				}
				else if( rhs == null ) {
					return( 1 );
				}
				else {
					CFLibDbKeyHash256 lhsPKey = lhs.getPKey();
					CFLibDbKeyHash256 rhsPKey = rhs.getPKey();
					int ret = lhsPKey.compareTo( rhsPKey );
					return( ret );
				}
			}
		};
		Collections.sort( arrayList, cmp );
		List<ICFSecSecClusRoleObj> sortedList = arrayList;
		return( sortedList );
	}

	@Override
	public List<ICFSecSecClusRoleObj> readSecClusRoleByNameIdx( String Name )
	{
		return( readSecClusRoleByNameIdx( Name,
			false ) );
	}

	@Override
	public List<ICFSecSecClusRoleObj> readSecClusRoleByNameIdx( String Name,
		boolean forceRead )
	{
		final String S_ProcName = "readSecClusRoleByNameIdx";
		ICFSecSecClusRoleByNameIdxKey key = schema.getCFSecBackingStore().getFactorySecClusRole().newByNameIdxKey();
		key.setRequiredName( Name );
		Map<CFLibDbKeyHash256, ICFSecSecClusRoleObj> dict;
		if( indexByNameIdx == null ) {
			indexByNameIdx = new HashMap< ICFSecSecClusRoleByNameIdxKey,
				Map< CFLibDbKeyHash256, ICFSecSecClusRoleObj > >();
		}
		if( ( ! forceRead ) && indexByNameIdx.containsKey( key ) ) {
			dict = indexByNameIdx.get( key );
		}
		else {
			dict = new HashMap<CFLibDbKeyHash256, ICFSecSecClusRoleObj>();
			ICFSecSecClusRoleObj obj;
			ICFSecSecClusRole[] recList = schema.getCFSecBackingStore().getTableSecClusRole().readDerivedByNameIdx( null,
				Name );
			ICFSecSecClusRole rec;
			for( int idx = 0; idx < recList.length; idx ++ ) {
				rec = recList[ idx ];
				obj = schema.getSecClusRoleTableObj().newInstance();
				obj.setPKey( rec.getPKey() );
				obj.setRec( rec );
				ICFSecSecClusRoleObj realised = (ICFSecSecClusRoleObj)obj.realise();
				dict.put( realised.getPKey(), realised );
			}
			indexByNameIdx.put( key, dict );
		}
		int len = dict.size();
		ICFSecSecClusRoleObj arr[] = new ICFSecSecClusRoleObj[len];
		Iterator<ICFSecSecClusRoleObj> valIter = dict.values().iterator();
		int idx = 0;
		while( ( idx < len ) && valIter.hasNext() ) {
			arr[idx++] = valIter.next();
		}
		if( idx < len ) {
			throw new CFLibArgumentUnderflowException( getClass(),
				S_ProcName,
				0,
				"idx",
				idx,
				len );
		}
		else if( valIter.hasNext() ) {
			throw new CFLibArgumentOverflowException( getClass(),
					S_ProcName,
					0,
					"idx",
					idx,
					len );
		}
		ArrayList<ICFSecSecClusRoleObj> arrayList = new ArrayList<ICFSecSecClusRoleObj>(len);
		for( idx = 0; idx < len; idx ++ ) {
			arrayList.add( arr[idx] );
		}

		Comparator<ICFSecSecClusRoleObj> cmp = new Comparator<ICFSecSecClusRoleObj>() {
			@Override
			public int compare( ICFSecSecClusRoleObj lhs, ICFSecSecClusRoleObj rhs ) {
				if( lhs == null ) {
					if( rhs == null ) {
						return( 0 );
					}
					else {
						return( -1 );
					}
				}
				else if( rhs == null ) {
					return( 1 );
				}
				else {
					CFLibDbKeyHash256 lhsPKey = lhs.getPKey();
					CFLibDbKeyHash256 rhsPKey = rhs.getPKey();
					int ret = lhsPKey.compareTo( rhsPKey );
					return( ret );
				}
			}
		};
		Collections.sort( arrayList, cmp );
		List<ICFSecSecClusRoleObj> sortedList = arrayList;
		return( sortedList );
	}

	@Override
	public ICFSecSecClusRoleObj readSecClusRoleByUNameIdx( CFLibDbKeyHash256 ClusterId,
		String Name )
	{
		return( readSecClusRoleByUNameIdx( ClusterId,
			Name,
			false ) );
	}

	@Override
	public ICFSecSecClusRoleObj readSecClusRoleByUNameIdx( CFLibDbKeyHash256 ClusterId,
		String Name, boolean forceRead )
	{
		if( indexByUNameIdx == null ) {
			indexByUNameIdx = new HashMap< ICFSecSecClusRoleByUNameIdxKey,
				ICFSecSecClusRoleObj >();
		}
		ICFSecSecClusRoleByUNameIdxKey key = schema.getCFSecBackingStore().getFactorySecClusRole().newByUNameIdxKey();
		key.setRequiredClusterId( ClusterId );
		key.setRequiredName( Name );
		ICFSecSecClusRoleObj obj = null;
		if( ( ! forceRead ) && indexByUNameIdx.containsKey( key ) ) {
			obj = indexByUNameIdx.get( key );
		}
		else {
			ICFSecSecClusRole rec = schema.getCFSecBackingStore().getTableSecClusRole().readDerivedByUNameIdx( null,
				ClusterId,
				Name );
			if( rec != null ) {
				obj = schema.getSecClusRoleTableObj().newInstance();
				obj.setRec( rec );
				obj.setPKey( rec.getPKey() );
				obj = (ICFSecSecClusRoleObj)obj.realise();
			}
		}
		return( obj );
	}

	@Override
	public ICFSecSecClusRoleObj readCachedSecClusRoleByIdIdx( CFLibDbKeyHash256 SecClusRoleId )
	{
		ICFSecSecClusRoleObj obj = null;
		obj = readCachedSecClusRole( SecClusRoleId );
		return( obj );
	}

	@Override
	public List<ICFSecSecClusRoleObj> readCachedSecClusRoleByClusterIdx( CFLibDbKeyHash256 ClusterId )
	{
		final String S_ProcName = "readCachedSecClusRoleByClusterIdx";
		ICFSecSecClusRoleByClusterIdxKey key = schema.getCFSecBackingStore().getFactorySecClusRole().newByClusterIdxKey();
		key.setRequiredClusterId( ClusterId );
		ArrayList<ICFSecSecClusRoleObj> arrayList = new ArrayList<ICFSecSecClusRoleObj>();
		if( indexByClusterIdx != null ) {
			Map<CFLibDbKeyHash256, ICFSecSecClusRoleObj> dict;
			if( indexByClusterIdx.containsKey( key ) ) {
				dict = indexByClusterIdx.get( key );
				int len = dict.size();
				ICFSecSecClusRoleObj arr[] = new ICFSecSecClusRoleObj[len];
				Iterator<ICFSecSecClusRoleObj> valIter = dict.values().iterator();
				int idx = 0;
				while( ( idx < len ) && valIter.hasNext() ) {
					arr[idx++] = valIter.next();
				}
				if( idx < len ) {
					throw new CFLibArgumentUnderflowException( getClass(),
						S_ProcName,
						0,
						"idx",
						idx,
						len );
				}
				else if( valIter.hasNext() ) {
					throw new CFLibArgumentOverflowException( getClass(),
							S_ProcName,
							0,
							"idx",
							idx,
							len );
				}
				for( idx = 0; idx < len; idx ++ ) {
					arrayList.add( arr[idx] );
				}
			}
		}
		else {
			ICFSecSecClusRoleObj obj;
			Iterator<ICFSecSecClusRoleObj> valIter = members.values().iterator();
			while( valIter.hasNext() ) {
				obj = valIter.next();
				if( obj != null ) {
					if( obj.getRec().compareTo( key ) == 0 ) {
						arrayList.add( obj );
					}
				}
			}
		}
		Comparator<ICFSecSecClusRoleObj> cmp = new Comparator<ICFSecSecClusRoleObj>() {
			@Override
			public int compare( ICFSecSecClusRoleObj lhs, ICFSecSecClusRoleObj rhs ) {
				if( lhs == null ) {
					if( rhs == null ) {
						return( 0 );
					}
					else {
						return( -1 );
					}
				}
				else if( rhs == null ) {
					return( 1 );
				}
				else {
					CFLibDbKeyHash256 lhsPKey = lhs.getPKey();
					CFLibDbKeyHash256 rhsPKey = rhs.getPKey();
					int ret = lhsPKey.compareTo( rhsPKey );
					return( ret );
				}
			}
		};
		Collections.sort( arrayList, cmp );
		return( arrayList );
	}

	@Override
	public List<ICFSecSecClusRoleObj> readCachedSecClusRoleByNameIdx( String Name )
	{
		final String S_ProcName = "readCachedSecClusRoleByNameIdx";
		ICFSecSecClusRoleByNameIdxKey key = schema.getCFSecBackingStore().getFactorySecClusRole().newByNameIdxKey();
		key.setRequiredName( Name );
		ArrayList<ICFSecSecClusRoleObj> arrayList = new ArrayList<ICFSecSecClusRoleObj>();
		if( indexByNameIdx != null ) {
			Map<CFLibDbKeyHash256, ICFSecSecClusRoleObj> dict;
			if( indexByNameIdx.containsKey( key ) ) {
				dict = indexByNameIdx.get( key );
				int len = dict.size();
				ICFSecSecClusRoleObj arr[] = new ICFSecSecClusRoleObj[len];
				Iterator<ICFSecSecClusRoleObj> valIter = dict.values().iterator();
				int idx = 0;
				while( ( idx < len ) && valIter.hasNext() ) {
					arr[idx++] = valIter.next();
				}
				if( idx < len ) {
					throw new CFLibArgumentUnderflowException( getClass(),
						S_ProcName,
						0,
						"idx",
						idx,
						len );
				}
				else if( valIter.hasNext() ) {
					throw new CFLibArgumentOverflowException( getClass(),
							S_ProcName,
							0,
							"idx",
							idx,
							len );
				}
				for( idx = 0; idx < len; idx ++ ) {
					arrayList.add( arr[idx] );
				}
			}
		}
		else {
			ICFSecSecClusRoleObj obj;
			Iterator<ICFSecSecClusRoleObj> valIter = members.values().iterator();
			while( valIter.hasNext() ) {
				obj = valIter.next();
				if( obj != null ) {
					if( obj.getRec().compareTo( key ) == 0 ) {
						arrayList.add( obj );
					}
				}
			}
		}
		Comparator<ICFSecSecClusRoleObj> cmp = new Comparator<ICFSecSecClusRoleObj>() {
			@Override
			public int compare( ICFSecSecClusRoleObj lhs, ICFSecSecClusRoleObj rhs ) {
				if( lhs == null ) {
					if( rhs == null ) {
						return( 0 );
					}
					else {
						return( -1 );
					}
				}
				else if( rhs == null ) {
					return( 1 );
				}
				else {
					CFLibDbKeyHash256 lhsPKey = lhs.getPKey();
					CFLibDbKeyHash256 rhsPKey = rhs.getPKey();
					int ret = lhsPKey.compareTo( rhsPKey );
					return( ret );
				}
			}
		};
		Collections.sort( arrayList, cmp );
		return( arrayList );
	}

	@Override
	public ICFSecSecClusRoleObj readCachedSecClusRoleByUNameIdx( CFLibDbKeyHash256 ClusterId,
		String Name )
	{
		ICFSecSecClusRoleObj obj = null;
		ICFSecSecClusRoleByUNameIdxKey key = schema.getCFSecBackingStore().getFactorySecClusRole().newByUNameIdxKey();
		key.setRequiredClusterId( ClusterId );
		key.setRequiredName( Name );
		if( indexByUNameIdx != null ) {
			if( indexByUNameIdx.containsKey( key ) ) {
				obj = indexByUNameIdx.get( key );
			}
			else {
				Iterator<ICFSecSecClusRoleObj> valIter = members.values().iterator();
				while( ( obj == null ) && valIter.hasNext() ) {
					obj = valIter.next();
					if( obj != null ) {
						if( obj.getRec().compareTo( key ) != 0 ) {
							obj = null;
						}
					}
				}
			}
		}
		else {
			Iterator<ICFSecSecClusRoleObj> valIter = members.values().iterator();
			while( valIter.hasNext() ) {
				obj = valIter.next();
				if( obj != null ) {
					if( obj.getRec().compareTo( key ) != 0 ) {
						obj = null;
					}
				}
			}
		}
		return( obj );
	}

	@Override
	public void deepDisposeSecClusRoleByIdIdx( CFLibDbKeyHash256 SecClusRoleId )
	{
		ICFSecSecClusRoleObj obj = readCachedSecClusRoleByIdIdx( SecClusRoleId );
		if( obj != null ) {
			obj.forget();
		}
	}

	@Override
	public void deepDisposeSecClusRoleByClusterIdx( CFLibDbKeyHash256 ClusterId )
	{
		final String S_ProcName = "deepDisposeSecClusRoleByClusterIdx";
		ICFSecSecClusRoleObj obj;
		List<ICFSecSecClusRoleObj> arrayList = readCachedSecClusRoleByClusterIdx( ClusterId );
		if( arrayList != null )  {
			Iterator<ICFSecSecClusRoleObj> arrayIter = arrayList.iterator();
			while( arrayIter.hasNext() ) {
				obj = arrayIter.next();
				if( obj != null ) {
					obj.forget();
				}
			}
		}
	}

	@Override
	public void deepDisposeSecClusRoleByNameIdx( String Name )
	{
		final String S_ProcName = "deepDisposeSecClusRoleByNameIdx";
		ICFSecSecClusRoleObj obj;
		List<ICFSecSecClusRoleObj> arrayList = readCachedSecClusRoleByNameIdx( Name );
		if( arrayList != null )  {
			Iterator<ICFSecSecClusRoleObj> arrayIter = arrayList.iterator();
			while( arrayIter.hasNext() ) {
				obj = arrayIter.next();
				if( obj != null ) {
					obj.forget();
				}
			}
		}
	}

	@Override
	public void deepDisposeSecClusRoleByUNameIdx( CFLibDbKeyHash256 ClusterId,
		String Name )
	{
		ICFSecSecClusRoleObj obj = readCachedSecClusRoleByUNameIdx( ClusterId,
				Name );
		if( obj != null ) {
			obj.forget();
		}
	}

	@Override
	public ICFSecSecClusRoleObj updateSecClusRole( ICFSecSecClusRoleObj Obj ) {
		ICFSecSecClusRoleObj obj = Obj;
		schema.getCFSecBackingStore().getTableSecClusRole().updateSecClusRole( null,
			Obj.getSecClusRoleRec() );
		obj = (ICFSecSecClusRoleObj)Obj.realise();
		obj.endEdit();
		return( obj );
	}

	@Override
	public void deleteSecClusRole( ICFSecSecClusRoleObj Obj ) {
		ICFSecSecClusRoleObj obj = Obj;
		schema.getCFSecBackingStore().getTableSecClusRole().deleteSecClusRole( null,
			obj.getSecClusRoleRec() );
		Obj.forget();
	}

	@Override
	public void deleteSecClusRoleByIdIdx( CFLibDbKeyHash256 SecClusRoleId )
	{
		ICFSecSecClusRoleObj obj = readSecClusRole(SecClusRoleId);
		if( obj != null ) {
			ICFSecSecClusRoleEditObj editObj = (ICFSecSecClusRoleEditObj)obj.getEdit();
			boolean editStarted;
			if( editObj == null ) {
				editObj = (ICFSecSecClusRoleEditObj)obj.beginEdit();
				if( editObj != null ) {
					editStarted = true;
				}
				else {
					editStarted = false;
				}
			}
			else {
				editStarted = false;
			}
			if( editObj != null ) {
				editObj.deleteInstance();
				if( editStarted ) {
					editObj.endEdit();
				}
			}
			obj.forget();
		}
		deepDisposeSecClusRoleByIdIdx( SecClusRoleId );
	}

	@Override
	public void deleteSecClusRoleByClusterIdx( CFLibDbKeyHash256 ClusterId )
	{
		ICFSecSecClusRoleByClusterIdxKey key = schema.getCFSecBackingStore().getFactorySecClusRole().newByClusterIdxKey();
		key.setRequiredClusterId( ClusterId );
		if( indexByClusterIdx == null ) {
			indexByClusterIdx = new HashMap< ICFSecSecClusRoleByClusterIdxKey,
				Map< CFLibDbKeyHash256, ICFSecSecClusRoleObj > >();
		}
		if( indexByClusterIdx.containsKey( key ) ) {
			Map<CFLibDbKeyHash256, ICFSecSecClusRoleObj> dict = indexByClusterIdx.get( key );
			schema.getCFSecBackingStore().getTableSecClusRole().deleteSecClusRoleByClusterIdx( null,
				ClusterId );
			Iterator<ICFSecSecClusRoleObj> iter = dict.values().iterator();
			ICFSecSecClusRoleObj obj;
			List<ICFSecSecClusRoleObj> toForget = new LinkedList<ICFSecSecClusRoleObj>();
			while( iter.hasNext() ) {
				obj = iter.next();
				toForget.add( obj );
			}
			iter = toForget.iterator();
			while( iter.hasNext() ) {
				obj = iter.next();
				obj.forget();
			}
			indexByClusterIdx.remove( key );
		}
		else {
			schema.getCFSecBackingStore().getTableSecClusRole().deleteSecClusRoleByClusterIdx( null,
				ClusterId );
		}
		deepDisposeSecClusRoleByClusterIdx( ClusterId );
	}

	@Override
	public void deleteSecClusRoleByNameIdx( String Name )
	{
		ICFSecSecClusRoleByNameIdxKey key = schema.getCFSecBackingStore().getFactorySecClusRole().newByNameIdxKey();
		key.setRequiredName( Name );
		if( indexByNameIdx == null ) {
			indexByNameIdx = new HashMap< ICFSecSecClusRoleByNameIdxKey,
				Map< CFLibDbKeyHash256, ICFSecSecClusRoleObj > >();
		}
		if( indexByNameIdx.containsKey( key ) ) {
			Map<CFLibDbKeyHash256, ICFSecSecClusRoleObj> dict = indexByNameIdx.get( key );
			schema.getCFSecBackingStore().getTableSecClusRole().deleteSecClusRoleByNameIdx( null,
				Name );
			Iterator<ICFSecSecClusRoleObj> iter = dict.values().iterator();
			ICFSecSecClusRoleObj obj;
			List<ICFSecSecClusRoleObj> toForget = new LinkedList<ICFSecSecClusRoleObj>();
			while( iter.hasNext() ) {
				obj = iter.next();
				toForget.add( obj );
			}
			iter = toForget.iterator();
			while( iter.hasNext() ) {
				obj = iter.next();
				obj.forget();
			}
			indexByNameIdx.remove( key );
		}
		else {
			schema.getCFSecBackingStore().getTableSecClusRole().deleteSecClusRoleByNameIdx( null,
				Name );
		}
		deepDisposeSecClusRoleByNameIdx( Name );
	}

	@Override
	public void deleteSecClusRoleByUNameIdx( CFLibDbKeyHash256 ClusterId,
		String Name )
	{
		if( indexByUNameIdx == null ) {
			indexByUNameIdx = new HashMap< ICFSecSecClusRoleByUNameIdxKey,
				ICFSecSecClusRoleObj >();
		}
		ICFSecSecClusRoleByUNameIdxKey key = schema.getCFSecBackingStore().getFactorySecClusRole().newByUNameIdxKey();
		key.setRequiredClusterId( ClusterId );
		key.setRequiredName( Name );
		ICFSecSecClusRoleObj obj = null;
		if( indexByUNameIdx.containsKey( key ) ) {
			obj = indexByUNameIdx.get( key );
			schema.getCFSecBackingStore().getTableSecClusRole().deleteSecClusRoleByUNameIdx( null,
				ClusterId,
				Name );
			obj.forget();
		}
		else {
			schema.getCFSecBackingStore().getTableSecClusRole().deleteSecClusRoleByUNameIdx( null,
				ClusterId,
				Name );
		}
		deepDisposeSecClusRoleByUNameIdx( ClusterId,
				Name );
	}
}