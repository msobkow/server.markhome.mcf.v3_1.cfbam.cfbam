// Description: Java 25 Table Object implementation for Index.

/*
 *	server.markhome.mcf.CFBam
 *
 *	Copyright (c) 2016-2026 Mark Stephen Sobkow
 *	
 *	Mark's Code Fractal 3.1 CFBam - Business Application Model
 *	
 *	This file is part of Mark's Code Fractal CFBam.
 *	
 *	Mark's Code Fractal CFBam is available under dual commercial license from
 *	Mark Stephen Sobkow, or under the terms of the GNU General Public License,
 *	Version 3 or later.
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
 *	
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
import server.markhome.mcf.v3_1.cfbam.cfbam.*;

public class CFBamIndexTableObj
	implements ICFBamIndexTableObj
{
	protected ICFBamSchemaObj schema;
	protected static int runtimeClassCode = ICFBamIndex.CLASS_CODE;
	protected static final int backingClassCode = ICFBamIndex.CLASS_CODE;
	private Map<CFLibDbKeyHash256, ICFBamIndexObj> members;
	private Map<CFLibDbKeyHash256, ICFBamIndexObj> allIndex;
	private Map< ICFBamScopeByTenantIdxKey,
		Map<CFLibDbKeyHash256, ICFBamIndexObj > > indexByTenantIdx;
	private Map< ICFBamIndexByUNameIdxKey,
		ICFBamIndexObj > indexByUNameIdx;
	private Map< ICFBamIndexByIdxTableIdxKey,
		Map<CFLibDbKeyHash256, ICFBamIndexObj > > indexByIdxTableIdx;
	private Map< ICFBamIndexByDefSchemaIdxKey,
		Map<CFLibDbKeyHash256, ICFBamIndexObj > > indexByDefSchemaIdx;
	public static String TABLE_NAME = "Index";
	public static String TABLE_DBNAME = "idxdef";

	public CFBamIndexTableObj() {
		schema = null;
		members = new HashMap<CFLibDbKeyHash256, ICFBamIndexObj>();
		allIndex = null;
		indexByTenantIdx = null;
		indexByUNameIdx = null;
		indexByIdxTableIdx = null;
		indexByDefSchemaIdx = null;
	}

	public CFBamIndexTableObj( ICFBamSchemaObj argSchema ) {
		schema = (ICFBamSchemaObj)argSchema;
		members = new HashMap<CFLibDbKeyHash256, ICFBamIndexObj>();
		allIndex = null;
		indexByTenantIdx = null;
		indexByUNameIdx = null;
		indexByIdxTableIdx = null;
		indexByDefSchemaIdx = null;
	}
	
	/**
	 *	Get class code always returns the runtime class code for the objects, which is not stable until the application is done initializing and registering its objects.
	 *
	 *	@return runtime classcode
	 */ 
	@Override
	public int getClassCode() {
		return CFBamIndexTableObj.getRuntimeClassCode();
	}	

	/**
	 *	Get the backing store schema's class code, which is hard-coded into the object hierarchy.
	 *
	 *	@return The hardcoded backing store class code for this object, which is only valid in that schema.
	 */
	public static int getBackingClassCode() {
		return( backingClassCode );
	}

	/**
	 *	Get the runtime class code for this table; this value is only stable after the application is fully initialized.
	 *
	 *	@return runtimeClassCode
	 */
	public static int getRuntimeClassCode() {
		return( runtimeClassCode );
	}

	/**
	 *	Set the runtime class code for this table; this is done only during application initialization by the SchemaObj's <tt>initClassCodes()</tt> static method,
	 *	which will only set the class codes once and never again.  Once set, the class codes are immutable within the application.
	 *	Application programmers should never invoke this method, so it has package access only.
	 *
	 *	@param	argNewClassCode	The runtime class code to be used by clients and integrated application logic to identify this table of this schema.
	 */
	static void setRuntimeClassCode(int argNewClassCode ) {
		if (argNewClassCode <= 0) {
			throw new CFLibArgumentUnderflowException(CFBamIndexTableObj.class, "setRuntimeClassCode", 1, "argNewClassCode", argNewClassCode, 1);
		}
		runtimeClassCode = argNewClassCode;
	}

	@Override
	public ICFBamSchemaObj getSchema() {
		return( schema );
	}

	@Override
	public void setSchema( ICFBamSchemaObj value ) {
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
		return( ICFBamSchemaDefObj.class );
	}


	@Override
	public void minimizeMemory() {
		allIndex = null;
		indexByTenantIdx = null;
		indexByUNameIdx = null;
		indexByIdxTableIdx = null;
		indexByDefSchemaIdx = null;
		List<ICFBamIndexObj> toForget = new LinkedList<ICFBamIndexObj>();
		ICFBamIndexObj cur = null;
		Iterator<ICFBamIndexObj> iter = members.values().iterator();
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
	 *	CFBamIndexObj.
	 */
	@Override
	public ICFBamIndexObj newInstance() {
		ICFBamIndexObj inst = new CFBamIndexObj( schema );
		return( inst );
	}

	/**
	 *	If your implementation subclasses the objects,
	 *	you'll want to overload the constructByClassCode()
	 *	implementation to return your implementation's
	 *	instances instead of the base implementation.
	 *
	 *	This is the sole factory for instances derived from
	 *	CFBamIndexObj.
	 */
	@Override
	public ICFBamIndexEditObj newEditInstance( ICFBamIndexObj orig ) {
		ICFBamIndexEditObj edit = new CFBamIndexEditObj( orig );
		return( edit );
	}

	@Override
	public ICFBamIndexObj realiseIndex( ICFBamIndexObj Obj ) {
		ICFBamIndexObj obj = Obj;
		CFLibDbKeyHash256 pkey = obj.getPKey();
		ICFBamIndexObj keepObj = null;
		if( members.containsKey( pkey ) && ( null != members.get( pkey ) ) ) {
			ICFBamIndexObj existingObj = members.get( pkey );
			keepObj = existingObj;

			/*
			 *	We always rebind the data because if we're being called, some index has
			 *	been updated and is refreshing it's data, which may or may not have changed
			 */

			// Detach object from alternate and duplicate indexes, leave PKey alone

			if( indexByTenantIdx != null ) {
				ICFBamScopeByTenantIdxKey keyTenantIdx =
					schema.getCFBamBackingStore().getFactoryScope().newByTenantIdxKey();
				keyTenantIdx.setRequiredTenantId( keepObj.getRequiredTenantId() );
				Map<CFLibDbKeyHash256, ICFBamIndexObj > mapTenantIdx = indexByTenantIdx.get( keyTenantIdx );
				if( mapTenantIdx != null ) {
					indexByTenantIdx.remove( keyTenantIdx );
				}
			}

			if( indexByUNameIdx != null ) {
				ICFBamIndexByUNameIdxKey keyUNameIdx =
					schema.getCFBamBackingStore().getFactoryIndex().newByUNameIdxKey();
				keyUNameIdx.setRequiredTableId( keepObj.getRequiredTableId() );
				keyUNameIdx.setRequiredName( keepObj.getRequiredName() );
				indexByUNameIdx.remove( keyUNameIdx );
			}

			if( indexByIdxTableIdx != null ) {
				ICFBamIndexByIdxTableIdxKey keyIdxTableIdx =
					schema.getCFBamBackingStore().getFactoryIndex().newByIdxTableIdxKey();
				keyIdxTableIdx.setRequiredTableId( keepObj.getRequiredTableId() );
				Map<CFLibDbKeyHash256, ICFBamIndexObj > mapIdxTableIdx = indexByIdxTableIdx.get( keyIdxTableIdx );
				if( mapIdxTableIdx != null ) {
					mapIdxTableIdx.remove( keepObj.getPKey() );
					if( mapIdxTableIdx.size() <= 0 ) {
						indexByIdxTableIdx.remove( keyIdxTableIdx );
					}
				}
			}

			if( indexByDefSchemaIdx != null ) {
				ICFBamIndexByDefSchemaIdxKey keyDefSchemaIdx =
					schema.getCFBamBackingStore().getFactoryIndex().newByDefSchemaIdxKey();
				keyDefSchemaIdx.setOptionalDefSchemaId( keepObj.getOptionalDefSchemaId() );
				Map<CFLibDbKeyHash256, ICFBamIndexObj > mapDefSchemaIdx = indexByDefSchemaIdx.get( keyDefSchemaIdx );
				if( mapDefSchemaIdx != null ) {
					mapDefSchemaIdx.remove( keepObj.getPKey() );
					if( mapDefSchemaIdx.size() <= 0 ) {
						indexByDefSchemaIdx.remove( keyDefSchemaIdx );
					}
				}
			}
			// Keep passing the new object because it's the one with the record
			// that the base table needs to copy to the existing object from
			// the cache.
			keepObj = (ICFBamIndexObj)schema.getScopeTableObj().realiseScope( Obj );

			// Attach new object to alternate and duplicate indexes -- PKey stay stable

			if( indexByTenantIdx != null ) {
				ICFBamScopeByTenantIdxKey keyTenantIdx =
					schema.getCFBamBackingStore().getFactoryScope().newByTenantIdxKey();
				keyTenantIdx.setRequiredTenantId( keepObj.getRequiredTenantId() );
				Map<CFLibDbKeyHash256, ICFBamIndexObj > mapTenantIdx = indexByTenantIdx.get( keyTenantIdx );
				if( mapTenantIdx != null ) {
					mapTenantIdx.put( keepObj.getPKey(), keepObj );
				}
			}

			if( indexByUNameIdx != null ) {
				ICFBamIndexByUNameIdxKey keyUNameIdx =
					schema.getCFBamBackingStore().getFactoryIndex().newByUNameIdxKey();
				keyUNameIdx.setRequiredTableId( keepObj.getRequiredTableId() );
				keyUNameIdx.setRequiredName( keepObj.getRequiredName() );
				indexByUNameIdx.put( keyUNameIdx, keepObj );
			}

			if( indexByIdxTableIdx != null ) {
				ICFBamIndexByIdxTableIdxKey keyIdxTableIdx =
					schema.getCFBamBackingStore().getFactoryIndex().newByIdxTableIdxKey();
				keyIdxTableIdx.setRequiredTableId( keepObj.getRequiredTableId() );
				Map<CFLibDbKeyHash256, ICFBamIndexObj > mapIdxTableIdx = indexByIdxTableIdx.get( keyIdxTableIdx );
				if( mapIdxTableIdx != null ) {
					mapIdxTableIdx.put( keepObj.getPKey(), keepObj );
				}
			}

			if( indexByDefSchemaIdx != null ) {
				ICFBamIndexByDefSchemaIdxKey keyDefSchemaIdx =
					schema.getCFBamBackingStore().getFactoryIndex().newByDefSchemaIdxKey();
				keyDefSchemaIdx.setOptionalDefSchemaId( keepObj.getOptionalDefSchemaId() );
				Map<CFLibDbKeyHash256, ICFBamIndexObj > mapDefSchemaIdx = indexByDefSchemaIdx.get( keyDefSchemaIdx );
				if( mapDefSchemaIdx != null ) {
					mapDefSchemaIdx.put( keepObj.getPKey(), keepObj );
				}
			}

			if( allIndex != null ) {
				allIndex.put( keepObj.getPKey(), keepObj );
			}
		}
		else {
			keepObj = obj;
			keepObj = (ICFBamIndexObj)schema.getScopeTableObj().realiseScope( keepObj );

			// Attach new object to PKey, all, alternate, and duplicate indexes
			members.put( keepObj.getPKey(), keepObj );
			if( allIndex != null ) {
				allIndex.put( keepObj.getPKey(), keepObj );
			}

			if( indexByTenantIdx != null ) {
				ICFBamScopeByTenantIdxKey keyTenantIdx =
					schema.getCFBamBackingStore().getFactoryScope().newByTenantIdxKey();
				keyTenantIdx.setRequiredTenantId( keepObj.getRequiredTenantId() );
				Map<CFLibDbKeyHash256, ICFBamIndexObj > mapTenantIdx = indexByTenantIdx.get( keyTenantIdx );
				if( mapTenantIdx != null ) {
					mapTenantIdx.put( keepObj.getPKey(), keepObj );
				}
			}

			if( indexByUNameIdx != null ) {
				ICFBamIndexByUNameIdxKey keyUNameIdx =
					schema.getCFBamBackingStore().getFactoryIndex().newByUNameIdxKey();
				keyUNameIdx.setRequiredTableId( keepObj.getRequiredTableId() );
				keyUNameIdx.setRequiredName( keepObj.getRequiredName() );
				indexByUNameIdx.put( keyUNameIdx, keepObj );
			}

			if( indexByIdxTableIdx != null ) {
				ICFBamIndexByIdxTableIdxKey keyIdxTableIdx =
					schema.getCFBamBackingStore().getFactoryIndex().newByIdxTableIdxKey();
				keyIdxTableIdx.setRequiredTableId( keepObj.getRequiredTableId() );
				Map<CFLibDbKeyHash256, ICFBamIndexObj > mapIdxTableIdx = indexByIdxTableIdx.get( keyIdxTableIdx );
				if( mapIdxTableIdx != null ) {
					mapIdxTableIdx.put( keepObj.getPKey(), keepObj );
				}
			}

			if( indexByDefSchemaIdx != null ) {
				ICFBamIndexByDefSchemaIdxKey keyDefSchemaIdx =
					schema.getCFBamBackingStore().getFactoryIndex().newByDefSchemaIdxKey();
				keyDefSchemaIdx.setOptionalDefSchemaId( keepObj.getOptionalDefSchemaId() );
				Map<CFLibDbKeyHash256, ICFBamIndexObj > mapDefSchemaIdx = indexByDefSchemaIdx.get( keyDefSchemaIdx );
				if( mapDefSchemaIdx != null ) {
					mapDefSchemaIdx.put( keepObj.getPKey(), keepObj );
				}
			}

		}
		return( keepObj );
	}

	@Override
	public ICFBamIndexObj createIndex( ICFBamIndexObj Obj ) {
		ICFBamIndexObj obj = Obj;
		ICFBamIndex rec = obj.getIndexRec();
		schema.getCFBamBackingStore().getTableIndex().createIndex(
			null,
			rec );
		obj.copyRecToPKey();
		if( obj.getClassCode() == runtimeClassCode ) {
			obj = (ICFBamIndexObj)(obj.realise());
		}
		obj.endEdit();
		return( obj );
	}

	@Override
	public ICFBamIndexObj readIndex( CFLibDbKeyHash256 pkey ) {
		return( readIndex( pkey, false ) );
	}

	@Override
	public ICFBamIndexObj readIndex( CFLibDbKeyHash256 pkey, boolean forceRead ) {
		ICFBamIndexObj obj = null;
		if( ( ! forceRead ) && members.containsKey( pkey ) ) {
			obj = members.get( pkey );
		}
		else {
			ICFBamIndex readRec = schema.getCFBamBackingStore().getTableIndex().readDerivedByIdIdx( null,
						pkey );
			if( readRec != null ) {
				obj = (ICFBamIndexObj)schema.getScopeTableObj().constructByClassCode( readRec.getClassCode() );
				obj.setPKey( readRec.getPKey() );
				obj.setRec( readRec );
				obj = (ICFBamIndexObj)obj.realise();
			}
		}
		return( obj );
	}

	@Override
	public ICFBamIndexObj readCachedIndex( CFLibDbKeyHash256 pkey ) {
		ICFBamIndexObj obj = null;
		if( members.containsKey( pkey ) ) {
			obj = members.get( pkey );
		}
		return( obj );
	}

	@Override
	public void reallyDeepDisposeIndex( ICFBamIndexObj obj )
	{
		final String S_ProcName = "CFBamIndexTableObj.reallyDeepDisposeIndex() ";
		String classCode;
		if( obj == null ) {
			return;
		}
		CFLibDbKeyHash256 pkey = obj.getPKey();
		ICFBamIndexObj existing = readCachedIndex( pkey );
		if( existing == null ) {
			return;
		}
		members.remove( pkey );
		ICFBamIndexByUNameIdxKey keyUNameIdx = schema.getCFBamBackingStore().getFactoryIndex().newByUNameIdxKey();
		keyUNameIdx.setRequiredTableId( existing.getRequiredTableId() );
		keyUNameIdx.setRequiredName( existing.getRequiredName() );

		ICFBamIndexByIdxTableIdxKey keyIdxTableIdx = schema.getCFBamBackingStore().getFactoryIndex().newByIdxTableIdxKey();
		keyIdxTableIdx.setRequiredTableId( existing.getRequiredTableId() );

		ICFBamIndexByDefSchemaIdxKey keyDefSchemaIdx = schema.getCFBamBackingStore().getFactoryIndex().newByDefSchemaIdxKey();
		keyDefSchemaIdx.setOptionalDefSchemaId( existing.getOptionalDefSchemaId() );


		ICFBamIndexColObj objDelIndexRefRelFromCols;
		List<ICFBamIndexColObj> arrDelIndexRefRelFromCols = schema.getIndexColTableObj().readCachedIndexColByIndexIdx( existing.getRequiredId() );
		Iterator<ICFBamIndexColObj> iterDelIndexRefRelFromCols = arrDelIndexRefRelFromCols.iterator();
		while( iterDelIndexRefRelFromCols.hasNext() ) {
			objDelIndexRefRelFromCols = iterDelIndexRefRelFromCols.next();
			if( objDelIndexRefRelFromCols != null ) {
						schema.getRelationColTableObj().deepDisposeRelationColByFromColIdx( objDelIndexRefRelFromCols.getRequiredId() );
			}
		}
		ICFBamIndexColObj objDelIndexRefRelToCols;
		List<ICFBamIndexColObj> arrDelIndexRefRelToCols = schema.getIndexColTableObj().readCachedIndexColByIndexIdx( existing.getRequiredId() );
		Iterator<ICFBamIndexColObj> iterDelIndexRefRelToCols = arrDelIndexRefRelToCols.iterator();
		while( iterDelIndexRefRelToCols.hasNext() ) {
			objDelIndexRefRelToCols = iterDelIndexRefRelToCols.next();
			if( objDelIndexRefRelToCols != null ) {
						schema.getRelationColTableObj().deepDisposeRelationColByToColIdx( objDelIndexRefRelToCols.getRequiredId() );
			}
		}
					schema.getIndexColTableObj().deepDisposeIndexColByIndexIdx( existing.getRequiredId() );

		if( indexByUNameIdx != null ) {
			indexByUNameIdx.remove( keyUNameIdx );
		}

		if( indexByIdxTableIdx != null ) {
			if( indexByIdxTableIdx.containsKey( keyIdxTableIdx ) ) {
				indexByIdxTableIdx.get( keyIdxTableIdx ).remove( pkey );
				if( indexByIdxTableIdx.get( keyIdxTableIdx ).size() <= 0 ) {
					indexByIdxTableIdx.remove( keyIdxTableIdx );
				}
			}
		}

		if( indexByDefSchemaIdx != null ) {
			if( indexByDefSchemaIdx.containsKey( keyDefSchemaIdx ) ) {
				indexByDefSchemaIdx.get( keyDefSchemaIdx ).remove( pkey );
				if( indexByDefSchemaIdx.get( keyDefSchemaIdx ).size() <= 0 ) {
					indexByDefSchemaIdx.remove( keyDefSchemaIdx );
				}
			}
		}


		schema.getScopeTableObj().reallyDeepDisposeScope( obj );
	}
	@Override
	public void deepDisposeIndex( CFLibDbKeyHash256 pkey ) {
		ICFBamIndexObj obj = readCachedIndex( pkey );
		if( obj != null ) {
			obj.forget();
		}
	}

	@Override
	public ICFBamIndexObj lockIndex( CFLibDbKeyHash256 pkey ) {
		ICFBamIndexObj locked = null;
		ICFBamIndex lockRec = schema.getCFBamBackingStore().getTableIndex().lockDerived( null, pkey );
		if( lockRec != null ) {
				locked = (ICFBamIndexObj)schema.getScopeTableObj().constructByClassCode( lockRec.getClassCode() );
			locked.setRec( lockRec );
			locked.setPKey( lockRec.getPKey() );
			locked = (ICFBamIndexObj)locked.realise();
		}
		else {
			throw new CFLibCollisionDetectedException( getClass(), "lockIndex", pkey );
		}
		return( locked );
	}

	@Override
	public List<ICFBamIndexObj> readAllIndex() {
		return( readAllIndex( false ) );
	}

	@Override
	public List<ICFBamIndexObj> readAllIndex( boolean forceRead ) {
		final String S_ProcName = "readAllIndex";
		if( ( allIndex == null ) || forceRead ) {
			Map<CFLibDbKeyHash256, ICFBamIndexObj> map = new HashMap<CFLibDbKeyHash256,ICFBamIndexObj>();
			allIndex = map;
			ICFBamIndex[] recList = schema.getCFBamBackingStore().getTableIndex().readAllDerived( null );
			ICFBamIndex rec;
			ICFBamIndexObj obj;
			for( int idx = 0; idx < recList.length; idx ++ ) {
				rec = recList[ idx ];
				obj = (ICFBamIndexObj)schema.getScopeTableObj().constructByClassCode( rec.getClassCode() );
				obj.setPKey( rec.getPKey() );
				obj.setRec( rec );
				ICFBamIndexObj realised = (ICFBamIndexObj)obj.realise();
			}
		}
		int len = allIndex.size();
		ICFBamIndexObj arr[] = new ICFBamIndexObj[len];
		Iterator<ICFBamIndexObj> valIter = allIndex.values().iterator();
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
		ArrayList<ICFBamIndexObj> arrayList = new ArrayList<ICFBamIndexObj>(len);
		for( idx = 0; idx < len; idx ++ ) {
			arrayList.add( arr[idx] );
		}

		Comparator<ICFBamIndexObj> cmp = new Comparator<ICFBamIndexObj>() {
			@Override
			public int compare( ICFBamIndexObj lhs, ICFBamIndexObj rhs ) {
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
		List<ICFBamIndexObj> sortedList = arrayList;
		return( sortedList );
	}

	@Override
	public List<ICFBamIndexObj> readCachedAllIndex() {
		final String S_ProcName = "readCachedAllIndex";
		ArrayList<ICFBamIndexObj> arrayList = new ArrayList<ICFBamIndexObj>();
		if( allIndex != null ) {
			int len = allIndex.size();
			ICFBamIndexObj arr[] = new ICFBamIndexObj[len];
			Iterator<ICFBamIndexObj> valIter = allIndex.values().iterator();
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
		Comparator<ICFBamIndexObj> cmp = new Comparator<ICFBamIndexObj>() {
			public int compare( ICFBamIndexObj lhs, ICFBamIndexObj rhs ) {
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
	public ICFBamIndexObj readIndexByIdIdx( CFLibDbKeyHash256 Id )
	{
		return( readIndexByIdIdx( Id,
			false ) );
	}

	@Override
	public ICFBamIndexObj readIndexByIdIdx( CFLibDbKeyHash256 Id, boolean forceRead )
	{
		ICFBamIndexObj obj = readIndex( Id, forceRead );
		return( obj );
	}

	@Override
	public List<ICFBamIndexObj> readIndexByTenantIdx( CFLibDbKeyHash256 TenantId )
	{
		return( readIndexByTenantIdx( TenantId,
			false ) );
	}

	@Override
	public List<ICFBamIndexObj> readIndexByTenantIdx( CFLibDbKeyHash256 TenantId,
		boolean forceRead )
	{
		final String S_ProcName = "readIndexByTenantIdx";
		ICFBamScopeByTenantIdxKey key = schema.getCFBamBackingStore().getFactoryScope().newByTenantIdxKey();
		key.setRequiredTenantId( TenantId );
		Map<CFLibDbKeyHash256, ICFBamIndexObj> dict;
		if( indexByTenantIdx == null ) {
			indexByTenantIdx = new HashMap< ICFBamScopeByTenantIdxKey,
				Map< CFLibDbKeyHash256, ICFBamIndexObj > >();
		}
		if( ( ! forceRead ) && indexByTenantIdx.containsKey( key ) ) {
			dict = indexByTenantIdx.get( key );
		}
		else {
			dict = new HashMap<CFLibDbKeyHash256, ICFBamIndexObj>();
			ICFBamScopeObj obj;
			ICFBamScope[] recList = schema.getCFBamBackingStore().getTableScope().readDerivedByTenantIdx( null,
				TenantId );
			ICFBamScope rec;
			for( int idx = 0; idx < recList.length; idx ++ ) {
				rec = recList[ idx ];
				obj = (ICFBamIndexObj)schema.getScopeTableObj().constructByClassCode( rec.getClassCode() );
				obj.setPKey( rec.getPKey() );
				obj.setRec( rec );
				ICFBamIndexObj realised = (ICFBamIndexObj)obj.realise();
				dict.put( realised.getPKey(), realised );
			}
			indexByTenantIdx.put( key, dict );
		}
		int len = dict.size();
		ICFBamIndexObj arr[] = new ICFBamIndexObj[len];
		Iterator<ICFBamIndexObj> valIter = dict.values().iterator();
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
		ArrayList<ICFBamIndexObj> arrayList = new ArrayList<ICFBamIndexObj>(len);
		for( idx = 0; idx < len; idx ++ ) {
			arrayList.add( arr[idx] );
		}

		Comparator<ICFBamIndexObj> cmp = new Comparator<ICFBamIndexObj>() {
			@Override
			public int compare( ICFBamIndexObj lhs, ICFBamIndexObj rhs ) {
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
		List<ICFBamIndexObj> sortedList = arrayList;
		return( sortedList );
	}

	@Override
	public ICFBamIndexObj readIndexByUNameIdx( CFLibDbKeyHash256 TableId,
		String Name )
	{
		return( readIndexByUNameIdx( TableId,
			Name,
			false ) );
	}

	@Override
	public ICFBamIndexObj readIndexByUNameIdx( CFLibDbKeyHash256 TableId,
		String Name, boolean forceRead )
	{
		if( indexByUNameIdx == null ) {
			indexByUNameIdx = new HashMap< ICFBamIndexByUNameIdxKey,
				ICFBamIndexObj >();
		}
		ICFBamIndexByUNameIdxKey key = schema.getCFBamBackingStore().getFactoryIndex().newByUNameIdxKey();
		key.setRequiredTableId( TableId );
		key.setRequiredName( Name );
		ICFBamIndexObj obj = null;
		if( ( ! forceRead ) && indexByUNameIdx.containsKey( key ) ) {
			obj = indexByUNameIdx.get( key );
		}
		else {
			ICFBamIndex rec = schema.getCFBamBackingStore().getTableIndex().readDerivedByUNameIdx( null,
				TableId,
				Name );
			if( rec != null ) {
				obj = (ICFBamIndexObj)schema.getScopeTableObj().constructByClassCode( rec.getClassCode() );
				obj.setRec( rec );
				obj.setPKey( rec.getPKey() );
				obj = (ICFBamIndexObj)obj.realise();
			}
		}
		return( obj );
	}

	@Override
	public List<ICFBamIndexObj> readIndexByIdxTableIdx( CFLibDbKeyHash256 TableId )
	{
		return( readIndexByIdxTableIdx( TableId,
			false ) );
	}

	@Override
	public List<ICFBamIndexObj> readIndexByIdxTableIdx( CFLibDbKeyHash256 TableId,
		boolean forceRead )
	{
		final String S_ProcName = "readIndexByIdxTableIdx";
		ICFBamIndexByIdxTableIdxKey key = schema.getCFBamBackingStore().getFactoryIndex().newByIdxTableIdxKey();
		key.setRequiredTableId( TableId );
		Map<CFLibDbKeyHash256, ICFBamIndexObj> dict;
		if( indexByIdxTableIdx == null ) {
			indexByIdxTableIdx = new HashMap< ICFBamIndexByIdxTableIdxKey,
				Map< CFLibDbKeyHash256, ICFBamIndexObj > >();
		}
		if( ( ! forceRead ) && indexByIdxTableIdx.containsKey( key ) ) {
			dict = indexByIdxTableIdx.get( key );
		}
		else {
			dict = new HashMap<CFLibDbKeyHash256, ICFBamIndexObj>();
			ICFBamIndexObj obj;
			ICFBamIndex[] recList = schema.getCFBamBackingStore().getTableIndex().readDerivedByIdxTableIdx( null,
				TableId );
			ICFBamIndex rec;
			for( int idx = 0; idx < recList.length; idx ++ ) {
				rec = recList[ idx ];
				obj = (ICFBamIndexObj)schema.getScopeTableObj().constructByClassCode( rec.getClassCode() );
				obj.setPKey( rec.getPKey() );
				obj.setRec( rec );
				ICFBamIndexObj realised = (ICFBamIndexObj)obj.realise();
				dict.put( realised.getPKey(), realised );
			}
			indexByIdxTableIdx.put( key, dict );
		}
		int len = dict.size();
		ICFBamIndexObj arr[] = new ICFBamIndexObj[len];
		Iterator<ICFBamIndexObj> valIter = dict.values().iterator();
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
		ArrayList<ICFBamIndexObj> arrayList = new ArrayList<ICFBamIndexObj>(len);
		for( idx = 0; idx < len; idx ++ ) {
			arrayList.add( arr[idx] );
		}

		Comparator<ICFBamIndexObj> cmp = new Comparator<ICFBamIndexObj>() {
			@Override
			public int compare( ICFBamIndexObj lhs, ICFBamIndexObj rhs ) {
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
		List<ICFBamIndexObj> sortedList = arrayList;
		return( sortedList );
	}

	@Override
	public List<ICFBamIndexObj> readIndexByDefSchemaIdx( CFLibDbKeyHash256 DefSchemaId )
	{
		return( readIndexByDefSchemaIdx( DefSchemaId,
			false ) );
	}

	@Override
	public List<ICFBamIndexObj> readIndexByDefSchemaIdx( CFLibDbKeyHash256 DefSchemaId,
		boolean forceRead )
	{
		final String S_ProcName = "readIndexByDefSchemaIdx";
		ICFBamIndexByDefSchemaIdxKey key = schema.getCFBamBackingStore().getFactoryIndex().newByDefSchemaIdxKey();
		key.setOptionalDefSchemaId( DefSchemaId );
		Map<CFLibDbKeyHash256, ICFBamIndexObj> dict;
		if( indexByDefSchemaIdx == null ) {
			indexByDefSchemaIdx = new HashMap< ICFBamIndexByDefSchemaIdxKey,
				Map< CFLibDbKeyHash256, ICFBamIndexObj > >();
		}
		if( ( ! forceRead ) && indexByDefSchemaIdx.containsKey( key ) ) {
			dict = indexByDefSchemaIdx.get( key );
		}
		else {
			dict = new HashMap<CFLibDbKeyHash256, ICFBamIndexObj>();
			ICFBamIndexObj obj;
			ICFBamIndex[] recList = schema.getCFBamBackingStore().getTableIndex().readDerivedByDefSchemaIdx( null,
				DefSchemaId );
			ICFBamIndex rec;
			for( int idx = 0; idx < recList.length; idx ++ ) {
				rec = recList[ idx ];
				obj = (ICFBamIndexObj)schema.getScopeTableObj().constructByClassCode( rec.getClassCode() );
				obj.setPKey( rec.getPKey() );
				obj.setRec( rec );
				ICFBamIndexObj realised = (ICFBamIndexObj)obj.realise();
				dict.put( realised.getPKey(), realised );
			}
			indexByDefSchemaIdx.put( key, dict );
		}
		int len = dict.size();
		ICFBamIndexObj arr[] = new ICFBamIndexObj[len];
		Iterator<ICFBamIndexObj> valIter = dict.values().iterator();
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
		ArrayList<ICFBamIndexObj> arrayList = new ArrayList<ICFBamIndexObj>(len);
		for( idx = 0; idx < len; idx ++ ) {
			arrayList.add( arr[idx] );
		}

		Comparator<ICFBamIndexObj> cmp = new Comparator<ICFBamIndexObj>() {
			@Override
			public int compare( ICFBamIndexObj lhs, ICFBamIndexObj rhs ) {
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
		List<ICFBamIndexObj> sortedList = arrayList;
		return( sortedList );
	}

	@Override
	public ICFBamIndexObj readCachedIndexByIdIdx( CFLibDbKeyHash256 Id )
	{
		ICFBamIndexObj obj = null;
		obj = readCachedIndex( Id );
		return( obj );
	}

	@Override
	public List<ICFBamIndexObj> readCachedIndexByTenantIdx( CFLibDbKeyHash256 TenantId )
	{
		final String S_ProcName = "readCachedIndexByTenantIdx";
		ICFBamScopeByTenantIdxKey key = schema.getCFBamBackingStore().getFactoryScope().newByTenantIdxKey();
		key.setRequiredTenantId( TenantId );
		ArrayList<ICFBamIndexObj> arrayList = new ArrayList<ICFBamIndexObj>();
		if( indexByTenantIdx != null ) {
			Map<CFLibDbKeyHash256, ICFBamIndexObj> dict;
			if( indexByTenantIdx.containsKey( key ) ) {
				dict = indexByTenantIdx.get( key );
				int len = dict.size();
				ICFBamIndexObj arr[] = new ICFBamIndexObj[len];
				Iterator<ICFBamIndexObj> valIter = dict.values().iterator();
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
			ICFBamIndexObj obj;
			Iterator<ICFBamIndexObj> valIter = members.values().iterator();
			while( valIter.hasNext() ) {
				obj = valIter.next();
				if( obj != null ) {
					if( obj.getRec().compareTo( key ) == 0 ) {
						arrayList.add( obj );
					}
				}
			}
		}
		Comparator<ICFBamIndexObj> cmp = new Comparator<ICFBamIndexObj>() {
			@Override
			public int compare( ICFBamIndexObj lhs, ICFBamIndexObj rhs ) {
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
	public ICFBamIndexObj readCachedIndexByUNameIdx( CFLibDbKeyHash256 TableId,
		String Name )
	{
		ICFBamIndexObj obj = null;
		ICFBamIndexByUNameIdxKey key = schema.getCFBamBackingStore().getFactoryIndex().newByUNameIdxKey();
		key.setRequiredTableId( TableId );
		key.setRequiredName( Name );
		if( indexByUNameIdx != null ) {
			if( indexByUNameIdx.containsKey( key ) ) {
				obj = indexByUNameIdx.get( key );
			}
			else {
				Iterator<ICFBamIndexObj> valIter = members.values().iterator();
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
			Iterator<ICFBamIndexObj> valIter = members.values().iterator();
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
	public List<ICFBamIndexObj> readCachedIndexByIdxTableIdx( CFLibDbKeyHash256 TableId )
	{
		final String S_ProcName = "readCachedIndexByIdxTableIdx";
		ICFBamIndexByIdxTableIdxKey key = schema.getCFBamBackingStore().getFactoryIndex().newByIdxTableIdxKey();
		key.setRequiredTableId( TableId );
		ArrayList<ICFBamIndexObj> arrayList = new ArrayList<ICFBamIndexObj>();
		if( indexByIdxTableIdx != null ) {
			Map<CFLibDbKeyHash256, ICFBamIndexObj> dict;
			if( indexByIdxTableIdx.containsKey( key ) ) {
				dict = indexByIdxTableIdx.get( key );
				int len = dict.size();
				ICFBamIndexObj arr[] = new ICFBamIndexObj[len];
				Iterator<ICFBamIndexObj> valIter = dict.values().iterator();
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
			ICFBamIndexObj obj;
			Iterator<ICFBamIndexObj> valIter = members.values().iterator();
			while( valIter.hasNext() ) {
				obj = valIter.next();
				if( obj != null ) {
					if( obj.getRec().compareTo( key ) == 0 ) {
						arrayList.add( obj );
					}
				}
			}
		}
		Comparator<ICFBamIndexObj> cmp = new Comparator<ICFBamIndexObj>() {
			@Override
			public int compare( ICFBamIndexObj lhs, ICFBamIndexObj rhs ) {
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
	public List<ICFBamIndexObj> readCachedIndexByDefSchemaIdx( CFLibDbKeyHash256 DefSchemaId )
	{
		final String S_ProcName = "readCachedIndexByDefSchemaIdx";
		ICFBamIndexByDefSchemaIdxKey key = schema.getCFBamBackingStore().getFactoryIndex().newByDefSchemaIdxKey();
		key.setOptionalDefSchemaId( DefSchemaId );
		ArrayList<ICFBamIndexObj> arrayList = new ArrayList<ICFBamIndexObj>();
		if( indexByDefSchemaIdx != null ) {
			Map<CFLibDbKeyHash256, ICFBamIndexObj> dict;
			if( indexByDefSchemaIdx.containsKey( key ) ) {
				dict = indexByDefSchemaIdx.get( key );
				int len = dict.size();
				ICFBamIndexObj arr[] = new ICFBamIndexObj[len];
				Iterator<ICFBamIndexObj> valIter = dict.values().iterator();
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
			ICFBamIndexObj obj;
			Iterator<ICFBamIndexObj> valIter = members.values().iterator();
			while( valIter.hasNext() ) {
				obj = valIter.next();
				if( obj != null ) {
					if( obj.getRec().compareTo( key ) == 0 ) {
						arrayList.add( obj );
					}
				}
			}
		}
		Comparator<ICFBamIndexObj> cmp = new Comparator<ICFBamIndexObj>() {
			@Override
			public int compare( ICFBamIndexObj lhs, ICFBamIndexObj rhs ) {
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
	public void deepDisposeIndexByIdIdx( CFLibDbKeyHash256 Id )
	{
		ICFBamIndexObj obj = readCachedIndexByIdIdx( Id );
		if( obj != null ) {
			obj.forget();
		}
	}

	@Override
	public void deepDisposeIndexByTenantIdx( CFLibDbKeyHash256 TenantId )
	{
		final String S_ProcName = "deepDisposeIndexByTenantIdx";
		ICFBamIndexObj obj;
		List<ICFBamIndexObj> arrayList = readCachedIndexByTenantIdx( TenantId );
		if( arrayList != null )  {
			Iterator<ICFBamIndexObj> arrayIter = arrayList.iterator();
			while( arrayIter.hasNext() ) {
				obj = arrayIter.next();
				if( obj != null ) {
					obj.forget();
				}
			}
		}
	}

	@Override
	public void deepDisposeIndexByUNameIdx( CFLibDbKeyHash256 TableId,
		String Name )
	{
		ICFBamIndexObj obj = readCachedIndexByUNameIdx( TableId,
				Name );
		if( obj != null ) {
			obj.forget();
		}
	}

	@Override
	public void deepDisposeIndexByIdxTableIdx( CFLibDbKeyHash256 TableId )
	{
		final String S_ProcName = "deepDisposeIndexByIdxTableIdx";
		ICFBamIndexObj obj;
		List<ICFBamIndexObj> arrayList = readCachedIndexByIdxTableIdx( TableId );
		if( arrayList != null )  {
			Iterator<ICFBamIndexObj> arrayIter = arrayList.iterator();
			while( arrayIter.hasNext() ) {
				obj = arrayIter.next();
				if( obj != null ) {
					obj.forget();
				}
			}
		}
	}

	@Override
	public void deepDisposeIndexByDefSchemaIdx( CFLibDbKeyHash256 DefSchemaId )
	{
		final String S_ProcName = "deepDisposeIndexByDefSchemaIdx";
		ICFBamIndexObj obj;
		List<ICFBamIndexObj> arrayList = readCachedIndexByDefSchemaIdx( DefSchemaId );
		if( arrayList != null )  {
			Iterator<ICFBamIndexObj> arrayIter = arrayList.iterator();
			while( arrayIter.hasNext() ) {
				obj = arrayIter.next();
				if( obj != null ) {
					obj.forget();
				}
			}
		}
	}

	@Override
	public ICFBamIndexObj updateIndex( ICFBamIndexObj Obj ) {
		ICFBamIndexObj obj = Obj;
		schema.getCFBamBackingStore().getTableIndex().updateIndex( null,
			Obj.getIndexRec() );
		if( Obj.getClassCode() == ((ICFBamSchemaObj)getSchema()).getIndexTableObj().getClassCode() ) {
			obj = (ICFBamIndexObj)Obj.realise();
		}
		obj.endEdit();
		return( obj );
	}

	@Override
	public void deleteIndex( ICFBamIndexObj Obj ) {
		ICFBamIndexObj obj = Obj;
		schema.getCFBamBackingStore().getTableIndex().deleteIndex( null,
			obj.getIndexRec() );
		Obj.forget();
	}

	@Override
	public void deleteIndexByIdIdx( CFLibDbKeyHash256 Id )
	{
		ICFBamIndexObj obj = readIndex(Id);
		if( obj != null ) {
			ICFBamIndexEditObj editObj = (ICFBamIndexEditObj)obj.getEdit();
			boolean editStarted;
			if( editObj == null ) {
				editObj = (ICFBamIndexEditObj)obj.beginEdit();
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
		deepDisposeIndexByIdIdx( Id );
	}

	@Override
	public void deleteIndexByTenantIdx( CFLibDbKeyHash256 TenantId )
	{
		ICFBamScopeByTenantIdxKey key = schema.getCFBamBackingStore().getFactoryScope().newByTenantIdxKey();
		key.setRequiredTenantId( TenantId );
		if( indexByTenantIdx == null ) {
			indexByTenantIdx = new HashMap< ICFBamScopeByTenantIdxKey,
				Map< CFLibDbKeyHash256, ICFBamIndexObj > >();
		}
		if( indexByTenantIdx.containsKey( key ) ) {
			Map<CFLibDbKeyHash256, ICFBamIndexObj> dict = indexByTenantIdx.get( key );
			schema.getCFBamBackingStore().getTableIndex().deleteIndexByTenantIdx( null,
				TenantId );
			Iterator<ICFBamIndexObj> iter = dict.values().iterator();
			ICFBamIndexObj obj;
			List<ICFBamIndexObj> toForget = new LinkedList<ICFBamIndexObj>();
			while( iter.hasNext() ) {
				obj = iter.next();
				toForget.add( obj );
			}
			iter = toForget.iterator();
			while( iter.hasNext() ) {
				obj = iter.next();
				obj.forget();
			}
			indexByTenantIdx.remove( key );
		}
		else {
			schema.getCFBamBackingStore().getTableIndex().deleteIndexByTenantIdx( null,
				TenantId );
		}
		deepDisposeIndexByTenantIdx( TenantId );
	}

	@Override
	public void deleteIndexByUNameIdx( CFLibDbKeyHash256 TableId,
		String Name )
	{
		if( indexByUNameIdx == null ) {
			indexByUNameIdx = new HashMap< ICFBamIndexByUNameIdxKey,
				ICFBamIndexObj >();
		}
		ICFBamIndexByUNameIdxKey key = schema.getCFBamBackingStore().getFactoryIndex().newByUNameIdxKey();
		key.setRequiredTableId( TableId );
		key.setRequiredName( Name );
		ICFBamIndexObj obj = null;
		if( indexByUNameIdx.containsKey( key ) ) {
			obj = indexByUNameIdx.get( key );
			schema.getCFBamBackingStore().getTableIndex().deleteIndexByUNameIdx( null,
				TableId,
				Name );
			obj.forget();
		}
		else {
			schema.getCFBamBackingStore().getTableIndex().deleteIndexByUNameIdx( null,
				TableId,
				Name );
		}
		deepDisposeIndexByUNameIdx( TableId,
				Name );
	}

	@Override
	public void deleteIndexByIdxTableIdx( CFLibDbKeyHash256 TableId )
	{
		ICFBamIndexByIdxTableIdxKey key = schema.getCFBamBackingStore().getFactoryIndex().newByIdxTableIdxKey();
		key.setRequiredTableId( TableId );
		if( indexByIdxTableIdx == null ) {
			indexByIdxTableIdx = new HashMap< ICFBamIndexByIdxTableIdxKey,
				Map< CFLibDbKeyHash256, ICFBamIndexObj > >();
		}
		if( indexByIdxTableIdx.containsKey( key ) ) {
			Map<CFLibDbKeyHash256, ICFBamIndexObj> dict = indexByIdxTableIdx.get( key );
			schema.getCFBamBackingStore().getTableIndex().deleteIndexByIdxTableIdx( null,
				TableId );
			Iterator<ICFBamIndexObj> iter = dict.values().iterator();
			ICFBamIndexObj obj;
			List<ICFBamIndexObj> toForget = new LinkedList<ICFBamIndexObj>();
			while( iter.hasNext() ) {
				obj = iter.next();
				toForget.add( obj );
			}
			iter = toForget.iterator();
			while( iter.hasNext() ) {
				obj = iter.next();
				obj.forget();
			}
			indexByIdxTableIdx.remove( key );
		}
		else {
			schema.getCFBamBackingStore().getTableIndex().deleteIndexByIdxTableIdx( null,
				TableId );
		}
		deepDisposeIndexByIdxTableIdx( TableId );
	}

	@Override
	public void deleteIndexByDefSchemaIdx( CFLibDbKeyHash256 DefSchemaId )
	{
		ICFBamIndexByDefSchemaIdxKey key = schema.getCFBamBackingStore().getFactoryIndex().newByDefSchemaIdxKey();
		key.setOptionalDefSchemaId( DefSchemaId );
		if( indexByDefSchemaIdx == null ) {
			indexByDefSchemaIdx = new HashMap< ICFBamIndexByDefSchemaIdxKey,
				Map< CFLibDbKeyHash256, ICFBamIndexObj > >();
		}
		if( indexByDefSchemaIdx.containsKey( key ) ) {
			Map<CFLibDbKeyHash256, ICFBamIndexObj> dict = indexByDefSchemaIdx.get( key );
			schema.getCFBamBackingStore().getTableIndex().deleteIndexByDefSchemaIdx( null,
				DefSchemaId );
			Iterator<ICFBamIndexObj> iter = dict.values().iterator();
			ICFBamIndexObj obj;
			List<ICFBamIndexObj> toForget = new LinkedList<ICFBamIndexObj>();
			while( iter.hasNext() ) {
				obj = iter.next();
				toForget.add( obj );
			}
			iter = toForget.iterator();
			while( iter.hasNext() ) {
				obj = iter.next();
				obj.forget();
			}
			indexByDefSchemaIdx.remove( key );
		}
		else {
			schema.getCFBamBackingStore().getTableIndex().deleteIndexByDefSchemaIdx( null,
				DefSchemaId );
		}
		deepDisposeIndexByDefSchemaIdx( DefSchemaId );
	}
}